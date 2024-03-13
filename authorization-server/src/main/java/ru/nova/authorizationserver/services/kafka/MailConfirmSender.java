package ru.nova.authorizationserver.services.kafka;

import ru.nova.authorizationserver.model.KafkaMailMessage;

public interface MailConfirmSender {
    void send(KafkaMailMessage value);
}
