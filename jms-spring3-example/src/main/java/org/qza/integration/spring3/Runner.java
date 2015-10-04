package org.qza.integration.spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author qza
 */
public class Runner {

	final Sender sender;
	final ApplicationContext context;

	public Runner() {
		context = new AnnotationConfigApplicationContext(Config.class);
		sender = context.getBean(Sender.class);
	}

	public void run() {
		sender.send();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Runner().run();
	}

}
