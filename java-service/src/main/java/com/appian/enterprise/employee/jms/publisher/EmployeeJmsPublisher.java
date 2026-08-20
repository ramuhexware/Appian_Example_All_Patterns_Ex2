package com.appian.enterprise.employee.jms.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Enterprise JMS Publisher representing Appian Outbound JMS Event Publisher.
 * Executed by Appian process models to emit asynchronous event messages to external queues.
 */
@Component
public class EmployeeJmsPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJmsPublisher.class);
    private final JmsTemplate jmsTemplate;

    public EmployeeJmsPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishEmployeeEvent(String destinationQueue, String eventPayload) {
        LOG.info("=== [JMS Outbound Publisher] Publishing Event Message to Queue [{}] ===", destinationQueue);
        LOG.info("Payload: {}", eventPayload);
        jmsTemplate.convertAndSend(destinationQueue, eventPayload);
    }
}
