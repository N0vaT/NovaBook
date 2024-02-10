package ru.nova.userapi.service;

import ru.nova.userapi.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long userId);
    User findByEmail(String userEmail);
    User save(User user);
    User changeUser(long id, User user);
}
