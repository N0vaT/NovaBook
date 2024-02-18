package ru.nova.clientnovabook.config;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.nova.clientnovabook.model.User;

import java.util.List;

@HttpExchange("http://127.0.0.1:8090")
public interface UserWebClient {

    @GetExchange("/users")
    List<User> getUsers();


}
