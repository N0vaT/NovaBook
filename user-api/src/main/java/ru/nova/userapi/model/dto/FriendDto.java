package ru.nova.userapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.userapi.model.User;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FriendDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private String avatarName;
    private User.Sex sex;
}
