package ru.nova.clientnovabook.service;

import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.Friend;
import ru.nova.clientnovabook.model.FriendInvite;
import ru.nova.clientnovabook.model.User;

@Service
public class FriendCheckerService {

    public FriendStatus check(User user, User userPage) {
        if(userPage.getUserId().equals(user.getUserId())){
            return FriendStatus.FRIEND;
        }
        if(userPage.getFriends().stream().
                map(Friend::getUserId)
                .anyMatch(id -> user.getUserId().equals(id))){
            return FriendStatus.FRIEND;
        }
        if(userPage.getResponseFriendInvites().stream()
                .map(FriendInvite::getUserFrom)
                .anyMatch(id -> user.getUserId().equals(id))){
            return FriendStatus.WAITING;
        }
        return FriendStatus.STRANGER;
    }

    public enum FriendStatus{
        FRIEND, WAITING, STRANGER
    }
}
