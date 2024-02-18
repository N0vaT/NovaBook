package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClientException;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.service.UserService;
import ru.nova.clientnovabook.service.WallService;

import java.security.Principal;

@Controller
@RequestMapping("/client/post")
@AllArgsConstructor
@Log4j2
public class PostController {
    private final UserService userService;
    private final WallService wallService;

    @PostMapping
    public String addPost(PostDto postDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        postDto.setOwnerId(user.getUserId());
        wallService.createPost(postDto);
        return "redirect:/client";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long postId, Principal principal){
//        User user;
//        try{
//            user = userService.findUserByEmail(principal.getName());
//            if()
//        }catch (WebClientException e){
//            throw new RuntimeException(); // TODO
//        }
        wallService.deletePost(postId);
        return "redirect:/client";
    }
}
