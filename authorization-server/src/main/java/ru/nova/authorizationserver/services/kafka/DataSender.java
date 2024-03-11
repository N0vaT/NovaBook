package ru.nova.authorizationserver.services.kafka;

import ru.nova.authorizationserver.model.StringValue;

public interface DataSender {
    void send(StringValue value);
}
