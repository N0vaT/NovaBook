package ru.nova.authorizationserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.JacksonUtils;
import ru.nova.authorizationserver.model.KafkaMailMessage;
import ru.nova.authorizationserver.services.kafka.MailConfirmSender;
import ru.nova.authorizationserver.services.kafka.MailConfirmSenderKafka;

@Configuration
@Log4j2
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public MailConfirmSender dataSender(NewTopic topic, KafkaTemplate<String, KafkaMailMessage> kafkaTemplate) {
        return new MailConfirmSenderKafka(
                topic.name(),
                kafkaTemplate,
                stringValue -> log.info("asked, value:{}", stringValue));
    }
}
