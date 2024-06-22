package ru.sinara.messageservicebackend.service.sendmailer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMailerImpl {
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String username;

    public RegistrationResponseDto send(RegistrationResponseDto messageRequestDto) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(username);
            simpleMailMessage.setTo(messageRequestDto.getEmail());
            simpleMailMessage.setSubject(messageRequestDto.getLogin());
            simpleMailMessage.setText(messageRequestDto.getRegistrationResponse().toString());
            emailSender.send(simpleMailMessage);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return messageRequestDto;
    }

}
