package ru.sinara.messageservicebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;
import ru.sinara.messageservicebackend.service.registration.RegistrationServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;
    @PostMapping
    public UUID registration(RegistrationRequestDto dto){
        return registrationService.createRegistration(dto);
    }
}
