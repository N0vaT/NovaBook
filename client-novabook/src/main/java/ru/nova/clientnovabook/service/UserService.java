package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.User;

public interface UserService {

    User findUserByName(String username);
//    void deleteUser(Long userId);
}
