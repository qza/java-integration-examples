package org.qza.integration.simple;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker {

	private Connection connection;
	private Session session;
	private Destination destination;

	private Logger log = LoggerFactory.getLogger(Broker.class);

	public Broker(ActiveMQConnectionFactory connectionFactory,
			String destinationName) {
		try {
			this.connection = connectionFactory.createConnection();
			this.connection.start();
			this.session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			this.destination = session.createQueue(destinationName);
		} catch (JMSException jmsex) {
			log.error("jms error:", jmsex);
		}
	}

	public Session getSession() {
		return this.session;
	}

	public MessageConsumer getConsumer() {
		try {
			return getSession().createConsumer(destination);
		} catch (JMSException e) {
			log.error("jms error:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public MessageProducer getProducer() {
		try {
			return getSession().createProducer(destination);
		} catch (JMSException e) {
			log.error("jms error:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public TextMessage createMessage(String text) {
		try {
			return getSession().createTextMessage(text);
		} catch (JMSException e) {
			log.error("jms error:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void closeAll() {
		try {
			if (connection != null) {
				this.connection.close();
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
