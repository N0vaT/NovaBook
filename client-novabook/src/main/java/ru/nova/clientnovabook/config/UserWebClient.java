package ru.nova.clientnovabook.config;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.nova.clientnovabook.model.User;

import java.util.List;

@HttpExchange("http://127.0.0.1:8090/users")
public interface UserWebClient {

    @GetExchange()
    List<User> getUsers();
    @GetExchange("/{id}")
    User getUserById(@PathVariable long id);
    @GetExchange()
    User getUserByEmail(@RequestParam("email") String email);
    @PostExchange()
    User createNewUser(@RequestBody() User user);
    @PutExchange("/")
    User editUser(@RequestBody() User user);

}
