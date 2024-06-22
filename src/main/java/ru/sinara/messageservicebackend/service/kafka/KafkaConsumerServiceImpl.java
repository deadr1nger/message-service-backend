package ru.sinara.messageservicebackend.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;
import ru.sinara.messageservicebackend.service.sendmailer.SendMailerImpl;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final SendMailerImpl sendMailerImpl;

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.in}")
    public void listen(@Payload String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RegistrationResponseDto convertedObject = mapper.readValue(message, RegistrationResponseDto.class);
        sendMailerImpl.send(convertedObject);
    }


}
