package org.qza.integration.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurer {

	private static final Logger log = LoggerFactory.getLogger(Configurer.class);

	public static Properties classpathProperties(String name) {

		Properties producerProperties = new Properties();
		try {
			InputStream propertiesStream = Configurer.class.getResourceAsStream(name);
			producerProperties.load(propertiesStream);
		} catch (IOException e) {
			log.error("io exception while loading properties", e);
		}
		return producerProperties;
	}

}
