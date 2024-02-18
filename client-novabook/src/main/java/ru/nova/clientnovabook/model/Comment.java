package ru.nova.clientnovabook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private Long ownerId;
    private Long postId;
    private String text;
    private LocalDateTime dateCreation;
    private String avatarName;
    private String name;
}
