package org.qza.integration.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qza
 */
public class Runner {

	final Producer producer;
	final Consumer consumer;

	final ApplicationContext context;
	final Logger log = LoggerFactory.getLogger(Runner.class);

	public Runner() {
		context = new ClassPathXmlApplicationContext("jms-context.xml");
		producer = context.getBean(Producer.class);
		consumer = context.getBean(Consumer.class);
	}

	public void run() {
		producer.produce();
		consumer.consume();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Runner().run();
	}

}
