package luma.jira.plugin.rtxnotifier;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class IssueCreatedResolvedListener implements InitializingBean, DisposableBean  {
	private static final Logger log = LoggerFactory.getLogger(IssueCreatedResolvedListener.class);

	private EventPublisher m_eventPublisher;
	
	public IssueCreatedResolvedListener(EventPublisher eventPublisher) {
		m_eventPublisher = eventPublisher;
	}
	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	   Long eventTypeId = issueEvent.getEventTypeId();
	   Issue issue = issueEvent.getIssue();
	 
	   if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
	      log.info("Issue {} has been created at {}.", issue.getKey(), issue.getCreated());
	   } else if (eventTypeId.equals(EventType.ISSUE_RESOLVED_ID)) {
	      log.info("Issue {} has been resolved at {}.", issue.getKey(), issue.getResolutionDate());
	   } else if (eventTypeId.equals(EventType.ISSUE_CLOSED_ID)) {
	      log.info("Issue {} has been closed at {}.", issue.getKey(), issue.getUpdated());
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