package ru.nova.wallapi.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nova.wallapi.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOwnerId(Long ownerId, PageRequest pageRequest);
    @Query("""
            SELECT COUNT(*) FROM Post p
            WHERE p.ownerId = :ownerId
            """)
    Integer countPostByOwnerId(Long ownerId);

}
