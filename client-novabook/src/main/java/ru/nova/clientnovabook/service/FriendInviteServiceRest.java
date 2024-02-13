package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.FriendInvite;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.webClient.UserWebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendInviteServiceRest implements FriendInviteService {
    private final UserWebClient userWebClient;

    @Override
    public void sendFriendRequest(User user, long idFriend) {
        FriendInvite invite = FriendInvite.builder()
                .userFrom(user.getUserId())
                .userTo(idFriend)
                .status(FriendInvite.InviteStatus.WAITING)
                .dateTime(LocalDateTime.now())
                .build();
        userWebClient.sendFriendRequest(idFriend, invite);
    }

    @Override
    public void declineFriendRequest(User user, long idFriend){
        FriendInvite invite = FriendInvite.builder()
                .userFrom(user.getUserId())
                .userTo(idFriend)
                .status(FriendInvite.InviteStatus.DENIED)
                .dateTime(LocalDateTime.now())
                .build();
        userWebClient.sendFriendRequest(idFriend, invite);
    }
}
