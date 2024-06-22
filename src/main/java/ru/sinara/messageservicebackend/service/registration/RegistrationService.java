package ru.sinara.messageservicebackend.service.registration;

import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

import java.util.UUID;

public interface RegistrationService {

    public UUID createRegistration(RegistrationRequestDto dto);


}
