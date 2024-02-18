package ru.nova.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nova.userapi.model.User;
import ru.nova.userapi.service.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor()
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public User getUserById(long id){
        User user = userService.findById(id);
        return user;
    }
    @GetMapping("/{id}")
    public User getUserByEmail(String email){
        User user = userService.findByEmail(email);
        return user;
    }
}
