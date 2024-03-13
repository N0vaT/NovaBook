package ru.nova.authorizationserver.model;

import java.time.LocalDateTime;

public record KafkaMailMessage(String email, String code, LocalDateTime date) {
}
