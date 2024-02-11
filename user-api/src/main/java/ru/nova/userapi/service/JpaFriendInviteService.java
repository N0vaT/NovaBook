package ru.nova.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.userapi.exception.FriendInviteNotFoundException;
import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.User;
import ru.nova.userapi.model.dto.FriendInviteDto;
import ru.nova.userapi.repository.FriendInviteRepository;
import ru.nova.userapi.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaFriendInviteService implements FriendInviteService{
    private final FriendInviteRepository friendInviteRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<FriendInvite> findAllByUserToId(long userId) {
        return friendInviteRepository.findAllByUserToUserId(userId);
    }

    @Override
    public List<FriendInvite> findAllByUserFromId(long userId) {
        return friendInviteRepository.findAllByUserFromUserId(userId);
    }

    @Override
    public FriendInvite findById(long inviteId) {
        return friendInviteRepository.findById(inviteId)
                .orElseThrow(() -> new FriendInviteNotFoundException("FriendInvite with id - " + inviteId + " not found"));
    }

    @Override
    @Transactional
    public FriendInvite save(FriendInviteDto inviteDto) {
        User userFrom = userService.findById(inviteDto.getUserFrom());
        User userTo = userService.findById(inviteDto.getUserTo());
        if(userTo.getRequestFriendInvites().stream().anyMatch(r -> r.getUserTo().equals(userFrom))){
            inviteDto.setStatus(FriendInvite.InviteStatus.ACCEPTED);
            userTo.getRequestFriendInvites().stream().filter(r -> r.getUserTo().equals(userFrom)).findAny().get().setStatus(FriendInvite.InviteStatus.ACCEPTED);
            userFrom.getFriends().add(userTo);
            userTo.getFriends().add(userFrom);
            userRepository.save(userTo);
            userRepository.save(userFrom);
        }
        FriendInvite invite = FriendInvite.builder()
                .inviteId(inviteDto.getInviteId())
                .userFrom(userFrom)
                .userTo(userTo)
                .status(inviteDto.getStatus())
                .dateTime(inviteDto.getDateTime())
                .build();
        return friendInviteRepository.save(invite);
    }

    @Override
    @Transactional
    public FriendInvite update(long inviteId, FriendInvite invite) {
        User userFrom = invite.getUserFrom();
        User userTo = invite.getUserTo();
        userFrom.getFriends().add(userTo);
        userTo.getFriends().add(userFrom);
        return friendInviteRepository.save(invite);
    }

    @Override
    @Transactional
    public void delete(long inviteId) {
        friendInviteRepository.deleteById(inviteId);
    }
}
