package ru.sinara.messageservicebackend.model.dto;

import lombok.Data;
import ru.sinara.messageservicebackend.model.RegistrationResponse;

import java.util.UUID;
@Data
public class RegistrationResponseDto {
    private UUID id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private RegistrationResponse registrationResponse;
}
