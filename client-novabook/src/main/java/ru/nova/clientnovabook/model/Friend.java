package ru.nova.clientnovabook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Friend {
    private Long userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private String avatarName;
    private User.Sex sex;
}
