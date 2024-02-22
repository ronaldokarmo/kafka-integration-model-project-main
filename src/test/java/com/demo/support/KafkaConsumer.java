package com.demo.support;

import com.demo.engine.Producer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    @Getter
    private CountDownLatch latch = new CountDownLatch(1);
    @Getter
    private String payload = null;
    @Getter
    private ConsumerRecord<String, String> consumerRecord = null;

    @SneakyThrows
    @KafkaListener(topics = {"${app.kafka.topic-out}"})
    public void receiveEvent(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        receive(consumerRecord, acknowledgment);
    }

    private void receive(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) throws com.fasterxml.jackson.core.JsonProcessingException {
        this.consumerRecord = consumerRecord;
        this.payload = this.consumerRecord.value();
        logger.info(String.format("#### -> Kafka Consumer received message -> %s", this.payload));
        acknowledgment.acknowledge();
        latch.countDown();
    }

    public void refreshLatch() {
        this.latch = new CountDownLatch(1);
    }

}
