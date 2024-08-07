package ru.sinara.messageservicebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;
import ru.sinara.messageservicebackend.service.registration.RegistrationServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;

    /**
     * @param dto - Тело запроса регистрации
     * @return - возвращает ID запроса
     */
    @PostMapping
    public RegistrationResponseDto registration(@RequestBody RegistrationRequestDto dto) {
        return registrationService.createRegistration(dto);
    }
}
