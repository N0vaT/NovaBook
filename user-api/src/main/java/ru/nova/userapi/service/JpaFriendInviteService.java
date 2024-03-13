package ru.nova.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nova.userapi.exception.FriendInviteNotFoundException;
import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.User;
import ru.nova.userapi.model.dto.FriendInviteDto;
import ru.nova.userapi.repository.FriendInviteRepository;
import ru.nova.userapi.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaFriendInviteService implements FriendInviteService{
    private final FriendInviteRepository friendInviteRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<FriendInvite> findAllByUserToId(long userId, int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return friendInviteRepository.findAllByUserToUserId(userId, pageRequest);
    }

    @Override
    public List<FriendInvite> findAllByUserFromId(long userId, int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return friendInviteRepository.findAllByUserFromUserId(userId, pageRequest);
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
        if(inviteDto.getStatus().equals(FriendInvite.InviteStatus.WAITING) &&
                userTo.getRequestFriendInvites().stream().
                anyMatch(r -> r.getUserTo().equals(userFrom) && r.getStatus().equals(FriendInvite.InviteStatus.WAITING))
        ){
            inviteDto.setStatus(FriendInvite.InviteStatus.ACCEPTED);
            userTo.getRequestFriendInvites().stream()
                    .filter(r -> r.getUserTo().equals(userFrom))
                    .findAny()
                    .get().setStatus(FriendInvite.InviteStatus.ACCEPTED);
            userTo.getRequestFriendInvites().stream()
                    .filter(r -> r.getUserTo().equals(userFrom))
                    .findAny()
                    .get().setDateTime(LocalDateTime.now());
            userFrom.getFriends().add(userTo);
            userTo.getFriends().add(userFrom);
            userRepository.save(userTo);
            userRepository.save(userFrom);
        }
        // DENIED + friend
        if(inviteDto.getStatus().equals(FriendInvite.InviteStatus.DENIED)
                && userFrom.getFriends().stream().anyMatch(u -> u.equals(userTo))
        ){
            FriendInvite inviteDeniedTo = userTo.getRequestFriendInvites().stream()
                    .filter(r -> r.getUserTo().equals(userFrom))
                    .findAny()
                    .get();
            inviteDeniedTo.setStatus(FriendInvite.InviteStatus.WAITING);
            inviteDeniedTo.setDateTime(LocalDateTime.now());
            userFrom.getFriends().remove(userTo);
            userTo.getFriends().remove(userFrom);
            userRepository.save(userTo);
            userRepository.save(userFrom);
            friendInviteRepository.save(inviteDeniedTo);
            long id = userFrom.getRequestFriendInvites().stream()
                    .filter(r -> r.getUserTo().equals(userTo)).findAny().get().getInviteId();
            inviteDto.setInviteId(id);
        }
        // DENIED + NOT friend
        if(inviteDto.getStatus().equals(FriendInvite.InviteStatus.DENIED)
                && userFrom.getFriends().stream().noneMatch(u -> u.equals(userTo))
        ){
            FriendInvite inviteDeniedTo = userTo.getRequestFriendInvites().stream()
                    .filter(r -> r.getUserTo().equals(userFrom))
                    .findAny()
                    .get();
            userTo.getRequestFriendInvites().remove(inviteDeniedTo);
            userRepository.save(userTo);
            return null;
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
