package com.project.projectapi.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.project.projectapi.dto.UserKafkaMessage;
import com.project.projectapi.service.UserService;

@Component
public class KafkaConsumerService {
    private final UserService userService;
    
    public KafkaConsumerService(UserService userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "user-topic", groupId = "my-consumer-group", containerFactory = "userKafkaListenerFactory")
    public void consume(UserKafkaMessage message){
        logger.info("Consumer Kafka Message: {}", message);

        userService.processKafkaUserAction(message);
    }

}
