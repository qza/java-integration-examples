package org.qza.integration.simple;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class Producer {

	private Broker broker;

	public Producer(Broker broker) {
		this.broker = broker;
	}

	public void produce() {
		MessageProducer producer = broker.getProducer();
		Message message = broker.createMessage("Text message from QZA :) ");
		try {
			producer.send(message);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				producer.close();
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
