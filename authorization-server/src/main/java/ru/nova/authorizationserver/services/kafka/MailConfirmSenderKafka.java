package ru.nova.authorizationserver.services.kafka;

import lombok.extern.log4j.Log4j2;

import org.springframework.kafka.core.KafkaTemplate;
import ru.nova.authorizationserver.model.KafkaMailMessage;

import java.util.function.Consumer;

@Log4j2
public class MailConfirmSenderKafka implements MailConfirmSender {

    private final KafkaTemplate<String, KafkaMailMessage> template;

    private final Consumer<KafkaMailMessage> sendAsk;

    private final String topic;

    public MailConfirmSenderKafka(
            String topic,
            KafkaTemplate<String, KafkaMailMessage> template,
            Consumer<KafkaMailMessage> sendAsk) {
        this.topic = topic;
        this.template = template;
        this.sendAsk = sendAsk;
    }

    @Override
    public void send(KafkaMailMessage value) {
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