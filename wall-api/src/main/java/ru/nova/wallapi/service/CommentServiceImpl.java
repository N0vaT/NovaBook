package ru.nova.wallapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.wallapi.exception.CommentNotFoundException;
import ru.nova.wallapi.model.Comment;
import ru.nova.wallapi.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }
    @Override
    public Comment findById(long commentId){
        return commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment with id - " + commentId + " not found")
                );
    }
    @Override
    @Transactional
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(long commentId, Comment comment) {
        comment.setCommentId(commentId);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(long commentId){
        commentRepository.deleteById(commentId);
    }
}
