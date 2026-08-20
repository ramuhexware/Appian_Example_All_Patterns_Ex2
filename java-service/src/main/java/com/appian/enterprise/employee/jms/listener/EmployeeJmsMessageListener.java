package com.appian.enterprise.employee.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Enterprise JMS Message Listener representing Appian Inbound JMS Messaging Integration.
 * Appian Listens to ActiveMQ/JMS topics or queues to trigger asynchronous Appian Process Models.
 */
@Component
public class EmployeeJmsMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJmsMessageListener.class);

    @JmsListener(destination = "employee.events.queue")
    public void receiveEmployeeEvent(String messagePayload) {
        LOG.info("=== [JMS Inbound Listener] Received JMS Message on Queue [employee.events.queue] ===");
        LOG.info("Payload: {}", messagePayload);
        LOG.info("Appian Process Triggered: Process Model ID [PM-EMP-EVENT-UPDATE]");
    }
}
