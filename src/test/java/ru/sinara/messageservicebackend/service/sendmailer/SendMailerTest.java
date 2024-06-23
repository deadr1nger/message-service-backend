package ru.sinara.messageservicebackend.service.sendmailer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ru.sinara.messageservicebackend.model.RegistrationResponse;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SendMailerTest {

    @InjectMocks
    SendMailerImpl sendMailer;
    @Mock
    JavaMailSender emailSender;

    @Test
    void sendMail() {
        RegistrationResponseDto dto = new RegistrationResponseDto(UUID.randomUUID(), "test", "test", "test", "test", "test", "test", RegistrationResponse.ALLOWED);
        sendMailer.send(dto);
    }
}
