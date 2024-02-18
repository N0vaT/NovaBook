package ru.nova.authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nova.authorizationserver.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}
