package ru.nova.clientnovabook.config;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.nova.clientnovabook.model.User;

import java.util.List;

@HttpExchange("http://127.0.0.1:8090/users")
public interface UserWebClient {

    @GetExchange()
    List<User> getUsers();
    @GetExchange("/{id}")
    User getUserById(@PathVariable long id);
    @GetExchange("/auth/{id}")
    User findUserByAuthorityId(long userAuthorityId);

}
