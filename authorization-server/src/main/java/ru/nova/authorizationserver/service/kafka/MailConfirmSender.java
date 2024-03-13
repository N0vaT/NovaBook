package ru.nova.authorizationserver.service.kafka;

import ru.nova.authorizationserver.model.StringValue;

public interface MailConfirmSender {
    void send(StringValue value);
}
