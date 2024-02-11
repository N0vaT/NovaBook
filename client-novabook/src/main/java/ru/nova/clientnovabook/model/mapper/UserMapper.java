package ru.nova.clientnovabook.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.UserDto;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final FriendInviteMapper friendInviteMapper;
    private final FriendMapper friendMapper;

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getUserId())
                .name(getFullName(user.getFirstName(), user.getLastName(), user.getPatronymic()))
                .phone(user.getPhone())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .registrationDate(user.getRegistrationDate())
                .avatarName(getAvatarName(user.getAvatarName(), user.getSex()))
                .sex(user.getSex())
                .friends(user.getFriends().stream()
                        .map(friendMapper::toDto)
                        .toList())
                .requestFriendInvites(user.getRequestFriendInvites().stream()
                        .map(friendInviteMapper::toDto)
                        .toList())
                .responseFriendInvites(user.getResponseFriendInvites().stream()
                        .map(friendInviteMapper::toDto)
                        .toList())
                .build();
    }

    public String getFullName(String firstName, String lastName, String patronymic){
        StringBuffer name = new StringBuffer();
        if(lastName != null){
            name.append(lastName);
        }
        if(firstName != null){
            if(!name.isEmpty()){
                name.append(' ');
            }
            name.append(firstName);
        }
        if(patronymic != null){
            if(!name.isEmpty()){
                name.append(' ');
            }
            name.append(patronymic);
        }
        if(name.isEmpty()){
            name.append("Без имени");
        }
        return name.toString();
    }

    public String getAvatarName(String avatarName, User.Sex sex){
        if(avatarName != null){
            return avatarName;
        }
        if(sex == null || sex.equals(User.Sex.NONE)){
            return "default.png";
        }else if(sex.equals(User.Sex.MAN)){
            return "default_m.jpeg";
        }else{
            return "default_w.jpeg";
        }
    }
}
