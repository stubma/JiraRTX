package luma.jira.plugin.rtxnotifier;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
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

	public IssueEventListener(UserManager userManager, EventPublisher eventPublisher, PluginSettingsFactory pluginSettingsFactory) {
		m_userManager = userManager;
		m_eventPublisher = eventPublisher;
		m_pluginSettingsFactory = pluginSettingsFactory;
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

		// build message
		if (eventTypeId.equals(EventType.ISSUE_ASSIGNED_ID)) {
			sb.append(String.format("%s已将问题%s分配给%s", m_userManager.getRemoteUser().getUsername(), issue.getKey(), issue.getAssignee().getName()));
			if (issueEvent.getComment() != null)
				sb.append(", 附加注释: ").append(issueEvent.getComment().getBody());
			log.info("Issue Event: ISSUE_ASSIGNED_ID, msg: {}", sb.toString());
		} else if (eventTypeId.equals(EventType.ISSUE_CLOSED_ID)) {
			log.info("Issue Event: ISSUE_CLOSED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENT_DELETED_ID)) {
			log.info("Issue Event: ISSUE_COMMENT_DELETED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENT_EDITED_ID)) {
			log.info("Issue Event: ISSUE_COMMENT_EDITED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_COMMENTED_ID)) {
			log.info("Issue Event: ISSUE_COMMENTED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
			log.info("Issue Event: ISSUE_CREATED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_DELETED_ID)) {
			log.info("Issue Event: ISSUE_DELETED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
			log.info("Issue Event: ISSUE_GENERICEVENT_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_MOVED_ID)) {
			log.info("Issue Event: ISSUE_MOVED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_REOPENED_ID)) {
			log.info("Issue Event: ISSUE_REOPENED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_RESOLVED_ID)) {
			log.info("Issue Event: ISSUE_RESOLVED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
			log.info("Issue Event: ISSUE_UPDATED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOG_DELETED_ID)) {
			log.info("Issue Event: ISSUE_WORKLOG_DELETED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOG_UPDATED_ID)) {
			log.info("Issue Event: ISSUE_WORKLOG_UPDATED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_WORKLOGGED_ID)) {
			log.info("Issue Event: ISSUE_WORKLOGGED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_WORKSTARTED_ID)) {
			log.info("Issue Event: ISSUE_WORKSTARTED_ID");
		} else if (eventTypeId.equals(EventType.ISSUE_WORKSTOPPED_ID)) {
			log.info("Issue Event: ISSUE_WORKSTOPPED_ID");
		}
		
		// set reporter as receiver
		receivers.add(issue.getReporter().getName());
		
		// build a json object
		JSONObject json = new JSONObject();
		try {
			json.put("msg", sb.toString());
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