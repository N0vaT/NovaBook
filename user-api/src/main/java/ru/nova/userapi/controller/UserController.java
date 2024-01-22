package ru.nova.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.userapi.exception.UserNotFoundException;
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
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String email){
        if(email == null){
            return ResponseEntity.ok(userService.findAll());
        }
        ResponseEntity<User> response;
        try {
            User user = userService.findByEmail(email);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }catch (UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        ResponseEntity<User> response;
        try{
            User user = userService.findById(id);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }catch (UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User editUser(@RequestBody User user){
        return userService.save(user);
    }

}
