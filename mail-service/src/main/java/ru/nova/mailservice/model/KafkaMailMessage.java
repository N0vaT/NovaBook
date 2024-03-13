package ru.nova.mailservice.model;

import java.time.LocalDateTime;

public record KafkaMailMessage(String email, String code, LocalDateTime date) {
}
