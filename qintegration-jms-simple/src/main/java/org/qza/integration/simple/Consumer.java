package org.qza.integration.simple;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;

public class Consumer {

	private Broker broker;

	public Consumer(Broker broker) {
		this.broker = broker;
	}

	public void consume() {
		MessageConsumer consumer = broker.getConsumer();
		try {
			Message message = consumer.receive();
			if (message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println(String.format("Message received: %s", text));
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				consumer.close();
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
			broker.closeAll();
		}
	}

}
