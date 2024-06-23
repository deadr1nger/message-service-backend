package ru.sinara.messageservicebackend.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinara.messageservicebackend.model.RegistrationResponse;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;
import ru.sinara.messageservicebackend.service.sendmailer.SendMailerImpl;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerTest {
    @InjectMocks
    KafkaConsumerServiceImpl kafkaConsumerService;
    @Mock
    SendMailerImpl sendMailer;

    @Test
    void listenerTest() throws JsonProcessingException {
        RegistrationResponseDto dto = new RegistrationResponseDto(UUID.randomUUID(), "test", "test", "test", "test", "test", "test", RegistrationResponse.ALLOWED);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(dto);
        kafkaConsumerService.listen(message);


    }
}
