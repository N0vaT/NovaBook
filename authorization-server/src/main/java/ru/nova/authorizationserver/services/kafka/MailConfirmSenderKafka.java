package ru.nova.authorizationserver.services.kafka;

import lombok.extern.log4j.Log4j2;

import org.springframework.kafka.core.KafkaTemplate;
import ru.nova.authorizationserver.model.StringValue;

import java.util.function.Consumer;

@Log4j2
public class MailConfirmSenderKafka implements MailConfirmSender {

    private final KafkaTemplate<String, StringValue> template;

    private final Consumer<StringValue> sendAsk;

    private final String topic;

    public MailConfirmSenderKafka(
            String topic,
            KafkaTemplate<String, StringValue> template,
            Consumer<StringValue> sendAsk) {
        this.topic = topic;
        this.template = template;
        this.sendAsk = sendAsk;
    }

    @Override
    public void send(StringValue value) {
        try {
            template.send(topic, value)
                    .whenComplete(
                            (result, ex) -> {
                                if (ex == null) {
                                    log.info(
                                            "Email({}) confirmation code:{} sent to Kafka, offset:{}",
                                            value.email(), value.code(),
                                            result.getRecordMetadata().offset());
                                    sendAsk.accept(value);
                                } else {
                                    log.error("Email confirmation code: {} was not sent to Kafka", value.email(), ex);
                                }
                            });
        } catch (Exception ex) {
            log.error("send error, value:{}", value, ex);
        }
    }
}