package ru.nova.wallapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.wallapi.exception.PostNotFoundException;
import ru.nova.wallapi.model.Comment;
import ru.nova.wallapi.model.CountPostByOwner;
import ru.nova.wallapi.model.Post;
import ru.nova.wallapi.service.CommentService;
import ru.nova.wallapi.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            @RequestParam(required = false, defaultValue = "dateCreation") String sortByField,
            @RequestParam(required = false) Long ownerId)
    {
        if(ownerId == null){
            return ResponseEntity.ok(postService.findAll(pageNumber, pageSize, direction, sortByField));
        }
        return ResponseEntity.ok(postService.findByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField));
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
        return postService.save(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Post editPost(@RequestBody Post post){
        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable long id) {
        boolean isRemoved = postService.deleteById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @GetMapping("/cnt")
    public CountPostByOwner getCountPostsByOwnerId(@RequestParam("ownerId") long ownerId){
        return postService.getCountPostsByOwnerId(ownerId);
    }
    @PostMapping("/{postId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public Comment addComment(@PathVariable long postId,
                       @RequestBody Comment comment){

        return commentService.save(comment);
    }
}
