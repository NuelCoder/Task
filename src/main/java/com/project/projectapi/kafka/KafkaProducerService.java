package com.project.projectapi.kafka;


import com.project.projectapi.dto.UserKafkaMessage;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, UserKafkaMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, UserKafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserKafkaMessage message) {
        kafkaTemplate.send("user-topic", message); 
    }
}