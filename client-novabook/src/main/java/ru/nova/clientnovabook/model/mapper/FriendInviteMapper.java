package ru.nova.clientnovabook.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.nova.clientnovabook.model.FriendInvite;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.FriendInviteDto;
import ru.nova.clientnovabook.service.UserService;

@Component
public class FriendInviteMapper {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public FriendInviteMapper(UserService userService, @Lazy UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public FriendInviteDto toDto(FriendInvite friendInvite){
        User userFrom = userService.findUserById(friendInvite.getUserFrom());
        User userTo = userService.findUserById(friendInvite.getUserTo());
        return FriendInviteDto.builder()
                .inviteId(friendInvite.getInviteId())
                .userFrom(friendInvite.getUserFrom())
                .nameFrom(userMapper.getFullName(userFrom.getFirstName(), userFrom.getLastName(), userFrom.getPatronymic()))
                .avatarFrom(userMapper.getAvatarName(userFrom.getAvatarName(), userFrom.getSex()))
                .userTo(friendInvite.getUserTo())
                .nameTo(userMapper.getFullName(userTo.getFirstName(), userTo.getLastName(), userTo.getPatronymic()))
                .avatarTo(userMapper.getAvatarName(userTo.getAvatarName(), userTo.getSex()))
                .status(friendInvite.getStatus())
                .dateTime(friendInvite.getDateTime())
                .build();
    }
}
