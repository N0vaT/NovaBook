package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.FriendInvite;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.FriendInviteDto;
import ru.nova.clientnovabook.webClient.UserWebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendInviteServiceRest implements FriendInviteService {
    private final UserWebClient userWebClient;
    private final UserService userService;

    @Override
    public void sendFriendRequest(User user, long userToId) {
        FriendInviteDto invite = FriendInviteDto.builder()
                .userFrom(user.getUserId())
                .userTo(userToId)
                .status(FriendInvite.InviteStatus.WAITING)
                .dateTime(LocalDateTime.now())
                .build();
        userWebClient.sendFriendRequest(userToId, invite);
    }
}
