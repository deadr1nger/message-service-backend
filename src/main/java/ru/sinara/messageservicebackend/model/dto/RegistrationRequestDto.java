package ru.sinara.messageservicebackend.model.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RegistrationRequestDto {

    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
}
