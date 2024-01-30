package ru.nova.wallapi.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nova.wallapi.model.Post;

import java.util.List;
import java.util.SortedMap;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOwnerId(Long ownerId, PageRequest pageRequest);

}
