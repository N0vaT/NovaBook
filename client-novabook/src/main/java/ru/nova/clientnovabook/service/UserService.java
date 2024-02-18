package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();
    User findUserById(long userId);
    User findUserByAuthorityId(long userAuthorityId);
//    User findUserByName(String username);
//    User getUserByEmail(String email);
//    void deleteUser(Long userId);
}
