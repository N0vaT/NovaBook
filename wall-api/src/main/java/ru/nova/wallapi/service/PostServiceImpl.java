package ru.nova.wallapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nova.wallapi.exception.PostNotFoundException;
import ru.nova.wallapi.model.CountPostByOwner;
import ru.nova.wallapi.model.Post;
import ru.nova.wallapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    public List<Post> findAll(int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return postRepository.findAll();
    }
    @Override
    public List<Post> findByOwnerId(Long ownerId, int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return postRepository.findAllByOwnerId(ownerId, pageRequest);
    }

    @Override
    public CountPostByOwner getCountPostsByOwnerId(Long ownerId){
        return CountPostByOwner.builder()
                .postCount(postRepository.countPostByOwnerId(ownerId))
                .build();
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
    public boolean deleteById(Long postId) {
        try{
            postRepository.deleteById(postId);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
