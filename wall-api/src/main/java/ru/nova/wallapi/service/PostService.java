package ru.nova.wallapi.service;

import ru.nova.wallapi.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll(int pageNumber, int pageSize, String direction, String sortByField);
    List<Post> findByOwnerId(Long ownerId, int pageNumber, int pageSize, String direction, String sortByField);
    Integer getCountPostsByOwnerId(Long ownerId);
    Post findById(Long postId);
    Post save(Post post);
    boolean deleteById(Long postId);
}
