package ru.nova.userapi.service;

import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.User;
import ru.nova.userapi.model.dto.FriendInviteDto;

import java.util.List;

public interface FriendInviteService {
    List<FriendInvite> findAllByUserToId(long userId, int pageNumber, int pageSize, String direction, String sortByField);
    List<FriendInvite> findAllByUserFromId(long userId, int pageNumber, int pageSize, String direction, String sortByField);
    FriendInvite findById(long inviteId);
    FriendInvite save(FriendInviteDto inviteDto);
    FriendInvite update(long inviteId, FriendInvite invite);
    void delete(long inviteId);
}
