package ru.nova.mailservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.nova.mailservice.config.properties.ApplicationProperties;
import ru.nova.mailservice.model.KafkaMailMessage;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final ApplicationProperties applicationProperties;
    @Override
    public void send(KafkaMailMessage kafkaMailMessage) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(applicationProperties.getEmailFrom());
        msg.setTo(kafkaMailMessage.email());
        msg.setSubject("Confirmation code");
        msg.setText("Confirmation code - " + kafkaMailMessage.code());
        try {
            mailSender.send(msg);
            log.info("email send, msg: {}", kafkaMailMessage);
        } catch (Exception e) {
            log.error("send mail error : {}", e.getMessage());
        }
    }
}
