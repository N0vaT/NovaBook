package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
    private LocalDateTime registrationDate;
    private String avatarName;
    private User.Sex sex;
}
