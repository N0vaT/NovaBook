package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.Comment;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.dto.AddCommentDto;
import ru.nova.clientnovabook.model.dto.PostDto;

import java.util.List;

public interface PostService {
    List<Post> findPosts();
    Post findPostById(long postId);
    List<Post> findPostsByOwnerId(long ownerId, int pageNumber, int pageSize, String direction, String sortByField);
    Integer getCountPostsByOwnerId(long ownerId);
    Post createPost(PostDto postDto);
    Post editPost(Post post);
    void deletePost(long postId);
    Comment addComment(AddCommentDto addCommentDto);
}
