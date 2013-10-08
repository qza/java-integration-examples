package org.qza.integration.spring3;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
@ComponentScan(basePackages = { "org.qza.integration.spring3" })
@PropertySource("classpath:jms.properties")
public class Config {

	@Autowired
	private Environment env;

	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ActiveMQConnectionFactory mqConnectionFactory() {
		String url = env.getProperty("broker.url");
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		return factory;
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ConnectionFactory connectionFactory() {
		ConnectionFactory factory = new CachingConnectionFactory(
				mqConnectionFactory());
		return factory;
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate(connectionFactory());
		String queueName = env.getProperty("queue.name");
		ActiveMQQueue queue = new ActiveMQQueue(queueName);
		template.setDefaultDestination(queue);
		return template;
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public Receiver receiver() {
		Receiver receiver = new Receiver();
		return receiver;
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public SimpleMessageListenerContainer simpleMLC() {
		SimpleMessageListenerContainer mlc = new SimpleMessageListenerContainer();
		String queueName = env.getProperty("queue.name");
		mlc.setConnectionFactory(connectionFactory());
		mlc.setDestinationName(queueName);
		mlc.setMessageListener(receiver());
		return mlc;
	}

}
