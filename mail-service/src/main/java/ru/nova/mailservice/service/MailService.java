package ru.nova.mailservice.service;

import ru.nova.mailservice.model.KafkaMailMessage;

public interface MailService {
    void send(KafkaMailMessage kafkaMailMessage);
}
