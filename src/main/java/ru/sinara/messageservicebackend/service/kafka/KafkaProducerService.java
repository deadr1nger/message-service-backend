package ru.sinara.messageservicebackend.service.kafka;

import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;

public interface KafkaProducerService {

    void send(RegistrationRequestDto message);
}
