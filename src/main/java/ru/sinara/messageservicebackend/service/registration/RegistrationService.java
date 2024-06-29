package ru.sinara.messageservicebackend.service.registration;

import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;

public interface RegistrationService {

    public void createRegistration(RegistrationRequestDto dto);


}
