package org.qza.integration.kafka;

import java.util.Optional;
import java.util.Properties;

import kafka.javaapi.producer.Producer;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class BasicProducer {

	private final String topic;
	private final Producer<Integer, String> producer;
	private final Properties classpathProperties = Configurer.classpathProperties("/producer.properties");

	public BasicProducer(String topic) {

		ProducerConfig producerConfig = new ProducerConfig(classpathProperties);

		this.producer = new Producer<>(producerConfig);
		this.topic = topic;
	}

	public BasicProducer(String topic, Properties producerProps) {

		Properties producerProperties = Optional.ofNullable(producerProps).orElse(classpathProperties);
		ProducerConfig producerConfig = new ProducerConfig(producerProperties);

		this.producer = new Producer<>(producerConfig);
		this.topic = topic;
	}

	public void produce(String text) {

		KeyedMessage<Integer, String> message = new KeyedMessage<>(topic, text);
		producer.send(message);
		producer.close();
	}

	public static void main(String[] args) {

		if (args == null || args.length != 2) {
			throw new IllegalArgumentException("required input: topic message");
		}

		BasicProducer producer = new BasicProducer(args[0]);
		producer.produce(args[1]);
	}

}
