package ru.nova.wallapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.wallapi.exception.PostNotFoundException;
import ru.nova.wallapi.model.Post;
import ru.nova.wallapi.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) Long ownerId)
    {
        if(ownerId == null){
            return ResponseEntity.ok(postService.findAll());
        }
        return ResponseEntity.ok(postService.findByOwnerId(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        ResponseEntity<Post> response;
        try{
            Post post = postService.findById(id);
            response = new ResponseEntity<>(post, HttpStatus.OK);
        }catch (PostNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Post savePost(@RequestBody Post post){
        System.out.println("");
        return postService.save(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Post editPost(@RequestBody Post post){
        return postService.save(post);
    }
}
