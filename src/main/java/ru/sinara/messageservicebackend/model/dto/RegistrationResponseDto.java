package ru.sinara.messageservicebackend.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.sinara.messageservicebackend.model.RegistrationResponse;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
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
