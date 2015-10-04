package org.qza.integration.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Receiver implements MessageListener {

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println(String.format("Message received: %s", text));
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}