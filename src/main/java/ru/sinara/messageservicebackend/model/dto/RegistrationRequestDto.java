package ru.sinara.messageservicebackend.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RegistrationRequestDto {

    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
}
