package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.webClient.WallWebClient;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestWallService implements WallService {
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
    public List<Post> findPostsByOwnerId(long ownerId) {
        return wallWebClient.getPostsByOwnerId(ownerId);
    }
    @Override
    public Post createPost(PostDto postDto) {
        Post post = new Post();
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
}
