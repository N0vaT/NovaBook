package ru.nova.authorizationserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.JacksonUtils;
import ru.nova.authorizationserver.model.StringValue;
import ru.nova.authorizationserver.services.kafka.DataSender;
import ru.nova.authorizationserver.services.kafka.DataSenderKafka;

@Configuration
@Log4j2
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public DataSender dataSender(NewTopic topic, KafkaTemplate<String, StringValue> kafkaTemplate) {
        return new DataSenderKafka(
                topic.name(),
                kafkaTemplate,
                stringValue -> log.info("asked, value:{}", stringValue));
    }
}
