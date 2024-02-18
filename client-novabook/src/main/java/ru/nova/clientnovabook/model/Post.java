package ru.nova.clientnovabook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long postId;
    private String postTitle;
    private String postText;
    private LocalDateTime dateCreation;
    private Long ownerId;
    private Status status;

    private List<Comment> comments;

    public enum Status{
        ACTIVE, ARCHIVED, CHANGED, DELETE
    }
}
