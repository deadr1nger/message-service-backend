package ru.sinara.messageservicebackend.service.registration;

import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

public interface RegistrationService {

    public RegistrationResponseDto createRegistration(RegistrationRequestDto dto);


}
