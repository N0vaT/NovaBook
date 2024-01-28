package ru.nova.wallapi.service;

import ru.nova.wallapi.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findByOwnerId(Long ownerId);
    Post findById(Long postId);
    Post save(Post post);
    void deleteById(Long postId);
}
