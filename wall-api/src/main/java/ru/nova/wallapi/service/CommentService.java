package ru.nova.wallapi.service;

import ru.nova.wallapi.exception.CommentNotFoundException;
import ru.nova.wallapi.model.Comment;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(long commentId);
    Comment save(Comment comment);
    Comment update(long commentId, Comment comment);
    void deleteById(long commentId);
}
