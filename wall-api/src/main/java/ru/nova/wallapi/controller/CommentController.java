package ru.nova.wallapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.wallapi.exception.CommentNotFoundException;
import ru.nova.wallapi.model.Comment;
import ru.nova.wallapi.model.Post;
import ru.nova.wallapi.service.CommentService;

@RestController
@RequestMapping("/post/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long commentId){
        ResponseEntity<Comment> response;
        try {
            Comment comment = commentService.findById(commentId);
            response = new ResponseEntity<>(comment, HttpStatus.OK);
        }catch (CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment editComment(@PathVariable long commentId,
                               @RequestBody Comment comment){
        System.out.println("");
        return commentService.update(commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable long commentId){
        commentService.deleteById(commentId);
    }

}
