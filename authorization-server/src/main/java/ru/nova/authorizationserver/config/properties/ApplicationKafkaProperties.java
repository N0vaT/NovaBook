package ru.nova.authorizationserver.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ApplicationKafkaProperties {
    @Value("${application.kafka.topic}")
    private String topicName;
}
