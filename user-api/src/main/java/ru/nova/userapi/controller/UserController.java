package ru.nova.userapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nova.userapi.model.User;

import javax.websocket.server.PathParam;

@RestController()
@RequestMapping("/demo")
public class UserController {

    @GetMapping()
    public User getUserByName(){
        System.out.println("APIIIIIIIIIIIIIIII");
        return new User(1L, "Ivan", "Ivanov");
    }
}
