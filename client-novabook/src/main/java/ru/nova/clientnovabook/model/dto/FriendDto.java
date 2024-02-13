package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDto {
    private Long userId;
    private String name;
    private LocalDate birthday;
    private String avatarName;
    private User.Sex sex;
}
