package ru.nova.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nova.userapi.model.User;
import ru.nova.userapi.service.UserService;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response;
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
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userService.findById(id);
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        return response;
    }
//    @GetMapping("/{id}")
//    public User getUserByEmail(String email){
//        User user = userService.findByEmail(email);
//        return user;
//    }
}
