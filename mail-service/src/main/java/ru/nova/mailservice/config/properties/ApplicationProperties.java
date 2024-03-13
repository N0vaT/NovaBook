package ru.nova.mailservice.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ApplicationProperties {
    @Value("${application.kafka.topic}")
    private String topicName;
    @Value("${application.email.from}")
    private String emailFrom;
}
