package org.qza.integration.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;

import kafka.javaapi.consumer.ConsumerConnector;

public class BasicConsumer {

	private final String topic;
	private final ConsumerConnector consumer;
	private final Properties classpathProperties = Configurer.classpathProperties("/consumer.properties");

	public BasicConsumer(String topic) {

		ConsumerConfig consumerConfig = new ConsumerConfig(classpathProperties);

		this.consumer = Consumer.createJavaConsumerConnector(consumerConfig);
		this.topic = topic;
	}

	public BasicConsumer(String topic, Properties consumerProps) {

		Properties consumerProperties = Optional.ofNullable(consumerProps).orElse(classpathProperties);
		ConsumerConfig consumerConfig = new ConsumerConfig(consumerProperties);

		this.consumer = Consumer.createJavaConsumerConnector(consumerConfig);
		this.topic = topic;
	}

	public void consume() {

		Map<String, Integer> topicCount = new HashMap<>();
		topicCount.put(topic, 1);
		
		consumer.createMessageStreams(topicCount).get(topic).forEach((stream) -> {
			
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                byte[] message = it.next().message();
				System.out.println("message consumed [topic: " + topic + "]: " + new String(message));
            }
            
		});
		
	}

	public static void main(String[] args) {

		if (args == null || args.length != 1) {
			throw new IllegalArgumentException("required input: topic");
		}

		BasicConsumer consumer = new BasicConsumer(args[0]);
		consumer.consume();
	}

}
