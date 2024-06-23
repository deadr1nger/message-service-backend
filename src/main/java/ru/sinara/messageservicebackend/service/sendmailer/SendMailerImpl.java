package ru.sinara.messageservicebackend.service.sendmailer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.sinara.messageservicebackend.model.dto.RegistrationResponseDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMailerImpl implements SendMailer {
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Метод отправления ответа на запрос на почту пользователя
     * @param dto - ответ на запрос регистрации
     *
     */
    @Override
    public void send(RegistrationResponseDto dto) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(username);
            simpleMailMessage.setTo(dto.getEmail());
            simpleMailMessage.setSubject(dto.getLogin());
            simpleMailMessage.setText(dto.getRegistrationResponse().toString());
            emailSender.send(simpleMailMessage);
            log.info("Send to user: {} respond {}", simpleMailMessage.getTo(), simpleMailMessage.getText());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
