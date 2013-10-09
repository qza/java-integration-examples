package org.qza.integration.camel;

public class Receiver {

	public void receive(String body) {
		System.out.println(String.format("Message received: %s", body));
	}

}