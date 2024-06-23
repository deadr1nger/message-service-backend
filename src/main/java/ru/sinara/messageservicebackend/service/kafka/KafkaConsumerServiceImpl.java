package ru.sinara.messageservicebackend.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;
import ru.sinara.messageservicebackend.service.sendmailer.SendMailerImpl;

@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final SendMailerImpl sendMailerImpl;

    /**
     * Метод получения запроса-подтверждения из кафки для последующей отправки результата пользователю по почте
     * @param message - тело запроса-подтверждения
     * @throws JsonProcessingException
     */
    @Override
    @KafkaListener(topics = "${spring.kafka.topic.in}")
    public void listen(@Payload String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RegistrationResponseDto convertedDto = mapper.readValue(message, RegistrationResponseDto.class);
        log.info(convertedDto.getId().toString(), convertedDto.getLogin(), convertedDto.getEmail(), convertedDto.getPassword(), convertedDto.getFirstName(), convertedDto.getLastName(), convertedDto.getMiddleName());
        sendMailerImpl.send(convertedDto);
    }


}
