package ru.nova.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.userapi.exception.UserNotFoundException;
import ru.nova.userapi.model.User;
import ru.nova.userapi.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(() ->
                new UserNotFoundException("User with email - " + userEmail + " not found"));
    }
    @Override
    public User findById(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User with id - " + userId + " not found"));
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
