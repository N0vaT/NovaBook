package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.Comment;
import ru.nova.clientnovabook.model.CountPostByOwner;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.dto.AddCommentDto;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.webClient.WallWebClient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestPostService implements PostService {
    private final WallWebClient wallWebClient;

    @Override
    public List<Post> findPosts() {
        return wallWebClient.getPosts();
    }
    @Override
    public Post findPostById(long postId) {
        return wallWebClient.getPostById(postId);
    }
    @Override
    public List<Post> findPostsByOwnerId(long ownerId, int pageNumber, int pageSize, String direction, String sortByField) {
        return wallWebClient.getPostsByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField);
    }
    @Override
    public CountPostByOwner getCountPostsByOwnerId(long ownerId) {
        return wallWebClient.getCountPostsByOwnerId(ownerId);
    }
    @Override
    public Post createPost(PostDto postDto) {
        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostText(postDto.getPostText());
        post.setDateCreation(LocalDateTime.now());
        post.setOwnerId(postDto.getOwnerId());
        post.setStatus(Post.Status.ACTIVE);
        return wallWebClient.createPost(post);
    }
    @Override
    public Post editPost(Post post) {
        return wallWebClient.editPost(post);
    }
    @Override
    public void deletePost(long postId) {
        wallWebClient.deletePost(postId);
    }
    @Override
    public Comment addComment(AddCommentDto addCommentDto){
        Comment comment = new Comment();
        comment.setOwnerId(addCommentDto.getOwnerId());
        comment.setPostId(addCommentDto.getPostId());
        comment.setText(addCommentDto.getText());
        comment.setDateCreation(LocalDateTime.now());
        return wallWebClient.addComment(comment.getPostId(), comment);
    }
}
