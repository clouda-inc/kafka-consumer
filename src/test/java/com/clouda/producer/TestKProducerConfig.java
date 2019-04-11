package com.clouda.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.clouda.consumer.KafkaConsumerApplication;

@Configuration
@Profile("producer-test")
@Import({ KafkaConsumerApplication.class })
public class TestKProducerConfig {
}
