package ru.nova.mailservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.nova.mailservice.config.properties.ApplicationProperties;
import ru.nova.mailservice.model.KafkaMailMessage;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class MailConfirmListener {
    private final MailService mailService;

    @KafkaListener(
            topics = "${application.kafka.topic}",
            containerFactory = "listenerContainerFactory")
    public void listen(@Payload List<KafkaMailMessage> mailMessages) {
        log.info("received {} messages from Kafka", mailMessages.size());
        for (KafkaMailMessage message: mailMessages){
            log.info("message({}) send to mailService", message);
            mailService.send(message);
        }
    }
}
