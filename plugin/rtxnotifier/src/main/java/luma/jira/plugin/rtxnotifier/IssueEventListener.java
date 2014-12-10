package luma.jira.plugin.rtxnotifier;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.sal.api.user.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class IssueEventListener implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(IssueEventListener.class);

	private EventPublisher m_eventPublisher;
	private UserManager m_userManager;

	public IssueEventListener(UserManager userManager, EventPublisher eventPublisher) {
		m_userManager = userManager;
		m_eventPublisher = eventPublisher;
	}

	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issue = issueEvent.getIssue();
		StringBuilder sb = new StringBuilder();

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
	}

	@Override
	public void destroy() throws Exception {
		m_eventPublisher.unregister(this);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		m_eventPublisher.register(this);
	}
}