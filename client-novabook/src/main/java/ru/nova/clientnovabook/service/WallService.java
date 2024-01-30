package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.dto.PostDto;

import java.util.List;

public interface WallService {
    List<Post> findPosts();
    Post findPostById(long postId);
    List<Post> findPostsByOwnerId(long ownerId);
    Post createPost(PostDto postDto);
    Post editPost(Post post);
    void deletePost(long postId);
}
