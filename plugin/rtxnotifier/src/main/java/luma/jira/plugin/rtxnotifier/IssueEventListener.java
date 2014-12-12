package luma.jira.plugin.rtxnotifier;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;

public class IssueEventListener implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(IssueEventListener.class);

	private EventPublisher m_eventPublisher;
	private UserManager m_userManager;
	private PluginSettingsFactory m_pluginSettingsFactory;
	
	// agent info
	private boolean m_agentConfigured;
	private String m_oldAgentAddr = "";
	private String m_oldAgentPort = "";
	
	// message send queue
	private Queue<JSONObject> m_sendQueue = new LinkedList<JSONObject>();
	
	// connection thread
	private Thread m_connThread;
	
	// string mapping
	private static Map<Long, String> s_eventTypeStrings;
	static {
		s_eventTypeStrings = new HashMap<Long, String>();
		s_eventTypeStrings.put(EventType.ISSUE_ASSIGNED_ID, "ISSUE_ASSIGNED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_CLOSED_ID, "ISSUE_CLOSED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_COMMENT_DELETED_ID, "ISSUE_COMMENT_DELETED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_COMMENT_EDITED_ID, "ISSUE_COMMENT_EDITED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_COMMENTED_ID, "ISSUE_COMMENTED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_CREATED_ID, "ISSUE_CREATED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_DELETED_ID, "ISSUE_DELETED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_GENERICEVENT_ID, "ISSUE_GENERICEVENT_ID");
		s_eventTypeStrings.put(EventType.ISSUE_MOVED_ID, "ISSUE_MOVED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_REOPENED_ID, "ISSUE_REOPENED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_RESOLVED_ID, "ISSUE_RESOLVED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_UPDATED_ID, "ISSUE_UPDATED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_WORKLOG_DELETED_ID, "ISSUE_WORKLOG_DELETED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_WORKLOG_UPDATED_ID, "ISSUE_WORKLOG_UPDATED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_WORKLOGGED_ID, "ISSUE_WORKLOGGED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_WORKSTARTED_ID, "ISSUE_WORKSTARTED_ID");
		s_eventTypeStrings.put(EventType.ISSUE_WORKSTOPPED_ID, "ISSUE_WORKSTOPPED_ID");
	}

	public IssueEventListener(UserManager userManager, EventPublisher eventPublisher, PluginSettingsFactory pluginSettingsFactory) {
		m_userManager = userManager;
		m_eventPublisher = eventPublisher;
		m_pluginSettingsFactory = pluginSettingsFactory;
	}
	
	private String getResolutionString(String rid) {
		if("1".equals(rid)) {
			return "已修正";
		} else if("2".equals(rid)) {
			return "不需要修正";
		} else if("3".equals(rid)) {
			return "和其它问题重复";
		} else if("4".equals(rid)) {
			return "问题描述不完整, 无法解决";
		} else if("5".equals(rid)) {
			return "无法重现";
		} else {
			return "";
		}
	}
	
	private String getTimeString(IssueEvent e) {
		Worklog wl = e.getWorklog();
		if(wl == null)
			return "无";
		if(wl.getTimeSpent() == null)
			return "无";
		
		int seconds = wl.getTimeSpent().intValue();
		int minutes = (seconds >= 60) ? (seconds / 60) : 0;
		int hours = (minutes >= 60) ? (minutes / 60) : 0;
		int days = (hours >= 8) ? (hours / 8) : 0;
		int weeks = (days >= 5) ? (days / 5) : 0;
		if(weeks > 0) {
			return String.format("%d星期", weeks);
		} else if(days > 0) {
			return String.format("%d天", days);
		} else if(hours > 0) {
			return String.format("%d小时", hours);
		} else if(minutes > 0) {
			return String.format("%d分钟", minutes);
		} else {
			return String.format("%d秒", seconds);
		}
	}

	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		// check agent
		checkAgent();
		if(!m_agentConfigured)
			return;
		
		// get info
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issue = issueEvent.getIssue();
		StringBuilder sb = new StringBuilder();
		List<String> receivers = new ArrayList<String>();
		String user = m_userManager.getRemoteUser().getFullName();
		String link = ComponentAccessor.getApplicationProperties().getString("jira.baseurl") + "/browse/" + issue.getKey();
		
		// build message
		if (eventTypeId.equals(EventType.ISSUE_ASSIGNED_ID)) {
			sb.append(String.format("%s已将问题%s([%s|%s])分配给%s", user, issue.getKey(), issue.getSummary(), 
					link, issue.getAssignee().getDisplayName()));
			if (issueEvent.getComment() != null)
				sb.append(", 注释: ").append(issueEvent.getComment().getBody());
		} else if (eventTypeId.equals(EventType.ISSUE_CLOSED_ID)) {
			sb.append(String.format("%s关闭了问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENT_DELETED_ID)) {
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENT_EDITED_ID)) {
			if(issueEvent.getComment() != null) {
				sb.append(String.format("%s修改了问题%s([%s|%s])的注释: %s", user, issue.getKey(), issue.getSummary(), 
						link, issueEvent.getComment().getBody()));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENTED_ID)) {
			if(issueEvent.getComment() != null) {
				sb.append(String.format("%s给问题%s([%s|%s])添加了注释: %s", user, issue.getKey(), issue.getSummary(), 
						link, issueEvent.getComment().getBody()));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
			sb.append(String.format("%s创建了问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
			if(issue.getAssignee() == null) {
				sb.append(", 尚未指定负责人");
			} else {
				sb.append(", 初始分配给").append(issue.getAssignee().getDisplayName());
			}
		} else if (eventTypeId.equals(EventType.ISSUE_DELETED_ID)) {
			sb.append(String.format("%s删除了问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
		} else if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
		} else if (eventTypeId.equals(EventType.ISSUE_MOVED_ID)) {
		} else if (eventTypeId.equals(EventType.ISSUE_REOPENED_ID)) {
			sb.append(String.format("%s重新打开问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
			if(issueEvent.getComment() != null) {
				sb.append(String.format(", 注释: %s", issueEvent.getComment().getBody()));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_RESOLVED_ID)) {
			sb.append(String.format("%s解决了问题%s([%s|%s]), 解决方案: %s", user, issue.getKey(), issue.getSummary(),
					link, getResolutionString(issue.getResolutionId())));
			if(issueEvent.getComment() != null) {
				sb.append(String.format(", 注释: %s", issueEvent.getComment().getBody()));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
			sb.append(String.format("%s更新了问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
			if(issueEvent.getComment() != null) {
				sb.append(String.format(", 注释: %s", issueEvent.getComment().getBody()));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOG_DELETED_ID)) {
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOG_UPDATED_ID)) {
			if(issueEvent.getWorklog() != null) {
				sb.append(String.format("%s更新了问题%s([%s|%s])的工作日志: %s, 工作时间: %s", user, issue.getKey(), issue.getSummary(),
						link, issueEvent.getWorklog().getComment(),
						getTimeString(issueEvent)));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOGGED_ID)) {
			if(issueEvent.getWorklog() != null) {
				sb.append(String.format("%s给问题%s([%s|%s])添加了工作日志: %s, 工作时间: %s", user, issue.getKey(), issue.getSummary(), 
						link, issueEvent.getWorklog().getComment(),
						getTimeString(issueEvent)));
			}
		} else if (eventTypeId.equals(EventType.ISSUE_WORKSTARTED_ID)) {
			sb.append(String.format("%s开始处理问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
		} else if (eventTypeId.equals(EventType.ISSUE_WORKSTOPPED_ID)) {
			sb.append(String.format("%s停止处理问题%s([%s|%s])", user, issue.getKey(), issue.getSummary(),
					link));
		}
		
		// log
		String msg = sb.toString();
		log.info("Issue Event: {}, msg: {}", s_eventTypeStrings.get(eventTypeId), msg);
		
		// if no message, don't send
		if("".equals(msg))
			return;
		
		// set reporter as receiver
		if(issue.getReporter() != null) {
			receivers.add(issue.getReporter().getName());
		}
		if(issue.getAssignee() != null) {
			receivers.add(issue.getAssignee().getName());
		}
		
		// build a json object
		JSONObject json = new JSONObject();
		try {
			json.put("msg", msg);
			json.put("receivers", receivers);
		} catch (JSONException e) {
		}
		
		// put to queue
		synchronized(this) {
			m_sendQueue.offer(json);
			notify();
		}
	}

	@Override
	public void destroy() throws Exception {
		m_eventPublisher.unregister(this);
		
		// end connection
		if(m_connThread != null) {
			try {
				m_connThread.interrupt();
			} catch (Exception e) {
			} finally {
				m_connThread = null;
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		m_eventPublisher.register(this);
		checkAgent();
	}
	
	private void checkAgent() {
		// get rtx agent address
		PluginSettings pluginSettings = m_pluginSettingsFactory.createGlobalSettings();
		String addr = (String)pluginSettings.get(AdminServlet.PLUGIN_STORAGE_KEY_AGENT_ADDRESS);
		String port = (String)pluginSettings.get(AdminServlet.PLUGIN_STORAGE_KEY_AGENT_PORT);
		
		// if agent address is not set, return
		if(addr == null || port == null) {
			m_agentConfigured = false;
			return;
		}
		m_agentConfigured = true;
		
		// if different, re-setup connection
		if(!m_oldAgentAddr.equals(addr) || !m_oldAgentPort.equals(port) || m_connThread == null) {
			m_oldAgentAddr = addr;
			m_oldAgentPort = port;
			startConnection();
		}
	}

	private void startConnection() {
		// end previous connection
		if(m_connThread != null) {
			try {
				m_connThread.interrupt();
			} catch (Exception e) {
			} finally {
				m_connThread = null;
			}
		}
		
		// start a new thread
		Thread t = new Thread() {
			@Override
			public void run() {
				Socket s = null;
				try {
					log.info("Connecting to RTX agent: {}:{}", m_oldAgentAddr, m_oldAgentPort);
					s = new Socket(m_oldAgentAddr, Integer.parseInt(m_oldAgentPort));
					OutputStream out = s.getOutputStream();
					log.info("Agent connection is established");
					
					while(true) {
						synchronized(IssueEventListener.this) {
							// send all
							while(m_sendQueue.size() > 0) {
								JSONObject json = m_sendQueue.poll();
								String str = json.toString();
								byte[] data = str.getBytes("utf-8");
								
								// write length as 4 bytes
								out.write((data.length >> 24) & 0xff);
								out.write((data.length >> 16) & 0xff);
								out.write((data.length >> 8) & 0xff);
								out.write(data.length & 0xff);
								
								// write data
								out.write(data);
								out.flush();
							}
						
							// wait
							IssueEventListener.this.wait();
						}
					}
				} catch (UnknownHostException e) {
					log.info("Cannot connect to RTX agent");
				} catch (Throwable e) {
					log.info("Agent connection is broken: {}", e.toString());
				} finally {
					m_connThread = null;
					try {  
		                if(s != null) {  
		                    s.close();  
		                }  
		            } catch (IOException e) {  
		            }  
				}
			}
		};
		m_connThread = t;
		t.start();
	}
}