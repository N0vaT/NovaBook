package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.User;

public interface UserService {

    Iterable<User> findAll();
    User findById(Long userId);

    void saveUser(User user);

    User addUser(User user);

    void deleteUser(Long userId);
}
