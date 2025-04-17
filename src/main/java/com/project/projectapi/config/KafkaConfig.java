package com.project.projectapi.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.project.projectapi.dto.UserKafkaMessage;

@EnableKafka
@Configuration
public class KafkaConfig {
    
    @Bean
    public ConsumerFactory<String,UserKafkaMessage> userConsumerFactory(){
        JsonDeserializer<UserKafkaMessage> deserializer = new JsonDeserializer<>(UserKafkaMessage.class);
        deserializer.addTrustedPackages("*");

         ErrorHandlingDeserializer<UserKafkaMessage> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(deserializer);


        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); 
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), errorHandlingDeserializer);
    }
     @Bean
     public ConcurrentKafkaListenerContainerFactory<String, UserKafkaMessage> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserKafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
}