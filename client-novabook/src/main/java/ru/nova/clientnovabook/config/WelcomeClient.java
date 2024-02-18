package ru.nova.clientnovabook.config;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.nova.clientnovabook.model.User;

@HttpExchange("http://127.0.0.1:8090")
public interface WelcomeClient {

    @GetExchange("/demo")
    User getWelcome();


}
