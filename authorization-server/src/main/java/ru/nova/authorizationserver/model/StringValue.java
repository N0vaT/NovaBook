package ru.nova.authorizationserver.model;

import java.time.LocalDateTime;

public record StringValue(String email, String code, LocalDateTime date) {
}
