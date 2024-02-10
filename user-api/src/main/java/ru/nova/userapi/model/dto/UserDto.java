package ru.nova.userapi.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String email;
    private LocalDate birthday;
    private LocalDateTime registrationDate;
    private String avatarName;
    private User.Sex sex;
    private List<FriendDto> friends;
    private List<FriendInviteDto> requestFriendInvites;
    private List<FriendInviteDto> responseFriendInvites;
}
