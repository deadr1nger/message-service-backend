package ru.sinara.messageservicebackend.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;

@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, RegistrationRequestDto> kafkaTemplate;
    @Value("${spring.kafka.topic.out}")
    private String topic;

    /**
     * Метод отправления запроса по кафке в сервис подтверждения регистрации
     * @param message
     */
    @Override
    public void send(RegistrationRequestDto message) {
        log.info("send registration values: {}, {}, {}, {}, {}, {}", message.getEmail(), message.getLogin(), message.getPassword(), message.getFirstName(), message.getLastName(), message.getMiddleName());
        kafkaTemplate.send(topic, message);
    }
}
