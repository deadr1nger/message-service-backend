package ru.sinara.messageservicebackend.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaConsumerService {
    void listen(String message) throws JsonProcessingException;
}
