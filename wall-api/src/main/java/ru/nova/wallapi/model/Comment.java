package ru.nova.wallapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nb_comments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "text")
    private String text;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "owner_id")
    private Long ownerId;
}
