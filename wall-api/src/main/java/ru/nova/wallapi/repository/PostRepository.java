package ru.nova.wallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nova.wallapi.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            SELECT p FROM Post p
            WHERE p.ownerId = :ownerId
            """)
    List<Post> findAllByOwnerId(Long ownerId);
}
