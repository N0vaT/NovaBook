package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();
    User findUserById(long userId);
    User findUserByEmail(String email);
    User createNewUser();
//    void deleteUser(Long userId);
    User save(User user);
}
