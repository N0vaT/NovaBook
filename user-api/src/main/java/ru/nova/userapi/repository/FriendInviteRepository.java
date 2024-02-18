package ru.nova.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nova.userapi.model.FriendInvite;

public interface FriendInviteRepository extends JpaRepository<FriendInvite, Long> {

}
