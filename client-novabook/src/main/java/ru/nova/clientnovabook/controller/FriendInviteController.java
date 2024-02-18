package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClientException;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.service.FriendInviteService;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/client/{id}/friend")
@AllArgsConstructor
@Log4j2
public class FriendInviteController {
    private final FriendInviteService friendInviteService;
    private final UserService userService;

    @PostMapping
    public String sendFriendRequest(@PathVariable long id,
                                    Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            throw new RuntimeException(); // TODO
        }
        friendInviteService.sendFriendRequest(user, id);
        return "redirect:/client/" + id;
    }
}
