package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import ru.nova.clientnovabook.exception.PostNotFoundException;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.AddCommentDto;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.model.dto.WallDto;
import ru.nova.clientnovabook.service.UserService;
import ru.nova.clientnovabook.service.PostService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client/post")
@AllArgsConstructor
@Log4j2
public class PostController {
    private final UserService userService;
    private final PostService postService;

    @PostMapping
    public String addPost(PostDto postDto, Principal principal){
        if(postDto.getPostText() == null || postDto.getPostText().equals("")){
            return "redirect:/client";
        }
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        postDto.setOwnerId(user.getUserId());
        postService.createPost(postDto);
        return "redirect:/client";
    }
    @PutMapping("/{id}")
    public String changePost(@PathVariable("id") long postId, String title, String text){
        if(text.equals("")){
            return "redirect:/client";
        }
        Post post = postService.findPostById(postId);
        if(!title.equals(post.getPostTitle()) || !text.equals(post.getPostText())){
            post.setPostTitle(title);
            post.setPostText(text);
            postService.editPost(post);
        }
        return "redirect:/client";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long postId, Principal principal){
//        User user;
//        try{
//            user = userService.findUserByEmail(principal.getName());
////            if()
//        }catch (WebClientException e){
//            throw new RuntimeException(); // TODO Сделать защиту на удаление
//        }
        postService.deletePost(postId);
        return "redirect:/client";
    }
    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") long postId,
                             AddCommentDto addCommentDto,
                             Principal principal)
    {
        if(addCommentDto.getText() == null || addCommentDto.getText().equals("")){
            return "redirect:/client";
        }
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        addCommentDto.setPostId(postId);
        addCommentDto.setOwnerId(user.getUserId());
        postService.addComment(addCommentDto);
        return "redirect:/client";
    }
    @DeleteMapping("/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable("postId") long postId,
                                @PathVariable("commentId") long commentId,
                                Principal principal)
    {
        //        User user;
//        try{
//            user = userService.findUserByEmail(principal.getName());
////            if()
//        }catch (WebClientException e){
//            throw new RuntimeException(); // TODO Сделать защиту на удаление
//        }
        postService.deleteComment(postId, commentId);
        return "redirect:/client";
    }
}
