package ru.nova.wallapi.service;

import ru.nova.wallapi.exception.CommentNotFoundException;
import ru.nova.wallapi.model.Comment;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(Long commentId);
    Comment save(Comment comment);
    void deleteById(Long commentId);
}
