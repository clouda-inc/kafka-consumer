package com.clouda.consumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("consumer-test")
@Import({ KafkaConsumerApplication.class })
public class TestKConsumerConfig {

}
