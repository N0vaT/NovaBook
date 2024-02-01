package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.webClient.UserWebClient;
import ru.nova.clientnovabook.model.User;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestUserService implements UserService{
    private final UserWebClient userWebClient;
    @Override
    public List<User> findUsers() {
        return userWebClient.getUsers();
    }
    @Override
    public User findUserById(long userId) {
        return userWebClient.getUserById(userId);
    }
    @Override
    public User findUserByEmail(String email) {
        return userWebClient.getUserByEmail(email);
    }
    @Override
    public User createNewUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = User.builder()
                .email(principal.getName())
                .registrationDate(LocalDateTime.now())
                .build();
        return userWebClient.createNewUser(user);
    }

    @Override
    public User save(User user) {
        return userWebClient.editUser(user);
    }
}
