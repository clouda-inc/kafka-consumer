package com.clouda.producer;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.clouda.consumer.Listener;
import com.clouda.consumer.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestKProducerConfig.class })
@ActiveProfiles("producer-test")
public class KafkaProducerApplicationTests {

	@Autowired
	private Producer producer;

	@Autowired
	private Listener listener;

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, "annotated1");

	@BeforeClass
	public static void setup() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getEmbeddedKafka().getBrokersAsString());
	}

	@Test
	public void producerTest() throws Exception {
		producer.send("foo");
		assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
	}

}
