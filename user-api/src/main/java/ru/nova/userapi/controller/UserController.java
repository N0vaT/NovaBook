package ru.nova.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.userapi.exception.UserNotFoundException;
import ru.nova.userapi.model.User;
import ru.nova.userapi.model.dto.FriendDto;
import ru.nova.userapi.model.dto.FriendInviteDto;
import ru.nova.userapi.model.dto.UserDto;
import ru.nova.userapi.service.UserService;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String email
    ){
        if(email == null){
            return ResponseEntity.ok(userService.findAll());
        }
        ResponseEntity<UserDto> response;
        try {
            User user = userService.findByEmail(email);
            response = new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        }catch (UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){
        ResponseEntity<UserDto> response;
        try{
            User user = userService.findById(id);
            response = new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        }catch (UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<FriendDto>> getUserFriends(@PathVariable long id){
        ResponseEntity<List<FriendDto>> response;
        try{
            User user = userService.findById(id);
            response = new ResponseEntity<>(user.getFriends().stream()
                    .map(u -> modelMapper.map(u, FriendDto.class))
                    .toList(), HttpStatus.OK);
        }catch (UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User saveUser(@RequestBody User user){
        System.out.println("");
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User editUser(@PathVariable long id,
                         @RequestBody User user){
        System.out.println("");
        return userService.changeUser(id, user);
    }

}
