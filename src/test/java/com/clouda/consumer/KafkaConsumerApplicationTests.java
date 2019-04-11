package com.clouda.consumer;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestKConsumerConfig.class })
@ActiveProfiles("consumer-test")
public class KafkaConsumerApplicationTests {

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, "annotated1");

	@BeforeClass
	public static void setup() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getEmbeddedKafka().getBrokersAsString());
	}

	@Autowired
	private Listener listener;

	@Autowired
	private KafkaTemplate<String, String> template;

	@Test
	public void consumerTest() throws Exception {
		template.send("annotated1", "data");
		assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
	}

}
