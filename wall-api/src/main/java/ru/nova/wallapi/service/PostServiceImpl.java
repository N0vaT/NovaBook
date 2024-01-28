package ru.nova.wallapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.wallapi.exception.PostNotFoundException;
import ru.nova.wallapi.model.Post;
import ru.nova.wallapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    @Override
    public List<Post> findByOwnerId(Long ownerId) {
        return postRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new PostNotFoundException("Post with id - " + postId + " not found")
                );
    }

    @Override
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }
}
