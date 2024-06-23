package ru.sinara.messageservicebackend.service.sendmailer;

import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

public interface SendMailer {

    void send(RegistrationResponseDto messageRequestDto);
}
