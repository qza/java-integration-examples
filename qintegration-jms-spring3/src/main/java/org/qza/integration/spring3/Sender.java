package org.qza.integration.spring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private JmsTemplate template;

	public void send() {
		template.convertAndSend("Test message");
	}

}