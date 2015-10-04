package org.qza.integration.camel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qza
 */
public class Runner {

	final ApplicationContext context;

	public Runner() {
		context = new ClassPathXmlApplicationContext("camel-context.xml");
	}

	public void run() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Runner().run();
	}

}
