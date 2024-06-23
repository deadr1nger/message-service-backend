package ru.sinara.messageservicebackend.service.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    @InjectMocks
    KafkaProducerServiceImpl kafkaProducerService;
    @Mock
    KafkaTemplate kafkaTemplate;

    @Test
    void producerTest() {
        RegistrationRequestDto dto = new RegistrationRequestDto("test", "test", "test", "test", "test", "test");
        kafkaProducerService.send(dto);
    }
}
