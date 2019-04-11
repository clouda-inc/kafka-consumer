package com.clouda.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String payload) {
		LOGGER.info("sending payload='{}'", payload);
		kafkaTemplate.send("annotated1", payload);
	}

}
