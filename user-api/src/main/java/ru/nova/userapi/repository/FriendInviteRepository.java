package ru.nova.userapi.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nova.userapi.model.FriendInvite;

import java.util.List;

public interface FriendInviteRepository extends JpaRepository<FriendInvite, Long> {

    List<FriendInvite> findAllByUserToUserId(long userId, PageRequest pageRequest);
    List<FriendInvite> findAllByUserFromUserId(long userId, PageRequest pageRequest);
}
