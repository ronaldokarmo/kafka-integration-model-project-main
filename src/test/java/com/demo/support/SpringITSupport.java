package com.demo.support;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EnableKafka
@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:3333", "port=3333"})
@CucumberContextConfiguration
public class SpringITSupport {

    @Autowired
    protected KafkaConsumer kafkaConsumer;
    @Autowired
    protected KafkaTemplate<String, String> kafkaProducer;
//    @Value("${app.kafka.topic-in}")
//    protected String topicIn;
//    @Value("${app.gateway.fare.url-path}")
//    protected String gatewayFareUrlPath;

}
