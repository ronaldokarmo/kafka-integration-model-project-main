package com.demo.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RecebeEventoRespondeEvento {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC_IN = "users";
    private static final String TOPIC_OUT = "resultado";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = TOPIC_IN, groupId = "app-consumer")
    public void consume(String message) throws IOException {
        String logMensagemRecebida = String.format("#### -> SpringApp consumed message -> %s", message);
        logger.info(logMensagemRecebida);
        this.sendMessage(logMensagemRecebida);
    }

    public void sendMessage(String message) {
        logger.info(String.format("#### -> SpringApp producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC_OUT, message);
    }

}
