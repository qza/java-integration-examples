package org.qza.integration.spring;

import org.springframework.jms.core.JmsTemplate;

public class Sender {
	
	private JmsTemplate template;

	public Sender(JmsTemplate template) {
		this.template = template;
	}

	public void send() {
		template.convertAndSend("Test message");
	}
	
}