package org.qza.integration.camel;

import org.apache.camel.builder.RouteBuilder;

public class Router extends RouteBuilder {
	
	private String queueName = "qintegration.camel.filetransfer.queue";
	private String dataDir = "src/main/resources/data";
	private String tmpFile = "//target/tmp";

	@Override
	public void configure() {
		from("file:" + dataDir + "?noop=true").to("jms:"+queueName);
		from("jms:"+queueName).to("file:" + tmpFile + "?noop=true");
		from("file:" + tmpFile + "?noop=true").bean(new Receiver());
	}

}
