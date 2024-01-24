package ru.nova.clientnovabook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String email;
    private LocalDate birthday;
    private LocalDateTime registrationDate;
    private String avatarName;
    private Sex sex;
    public enum Sex{
        NONE(), WOMAN, MAN;
    }

}
