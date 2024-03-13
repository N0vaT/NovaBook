package ru.nova.authorizationserver.services.kafka;

import ru.nova.authorizationserver.model.StringValue;

public interface MailConfirmSender {
    void send(StringValue value);
}
