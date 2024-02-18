package ru.nova.wallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nova.wallapi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
