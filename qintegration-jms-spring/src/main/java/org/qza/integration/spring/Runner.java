package org.qza.integration.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qza
 */
public class Runner {

	final Sender sender;
	final Receiver receiver;

	final ApplicationContext context;
	final Logger log = LoggerFactory.getLogger(Runner.class);

	public Runner() {
		context = new ClassPathXmlApplicationContext("jms-context.xml");
		sender = context.getBean(Sender.class);
		receiver = context.getBean(Receiver.class);
	}

	public void run() {
		sender.send();
		zzz(1000);
	}

	private void zzz(long msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException ex) {
			// ignore
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Runner().run();
	}

}
