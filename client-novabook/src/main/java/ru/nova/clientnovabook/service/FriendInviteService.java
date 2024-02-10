package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.User;

public interface FriendInviteService {

    void sendFriendRequest(User user, long id);
}
