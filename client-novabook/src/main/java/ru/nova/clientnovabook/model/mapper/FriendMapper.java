package ru.nova.clientnovabook.model.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nova.clientnovabook.model.Friend;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.FriendDto;
import ru.nova.clientnovabook.service.UserService;

import java.time.LocalDate;

@Component
public class FriendMapper {
    private final UserMapper userMapper;
    @Autowired
    public FriendMapper(@Lazy UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public FriendDto toDto(Friend friend){
        return FriendDto.builder()
                .userId(friend.getUserId())
                .name(userMapper.getFullName(friend.getFirstName(), friend.getLastName(), friend.getPatronymic()))
                .birthday(friend.getBirthday())
                .avatarName(userMapper.getAvatarName(friend.getAvatarName(), friend.getSex()))
                .sex(friend.getSex())
                .build();
    }
}
