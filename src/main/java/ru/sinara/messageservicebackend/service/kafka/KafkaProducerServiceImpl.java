package ru.sinara.messageservicebackend.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, RegistrationRequestDto> kafkaTemplate;
    @Value("${spring.kafka.topic.out}")
    private String topic;
    @Override
    public void send(RegistrationRequestDto message) {
        kafkaTemplate.send(topic, message);
    }
}
