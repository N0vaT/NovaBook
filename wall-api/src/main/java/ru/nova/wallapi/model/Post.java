package ru.nova.wallapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nb_posts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_text")
    private String postText;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "nb_posts_comments",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments;

    public enum Status{
        ACTIVE, ARCHIVED, CHANGED, DELETE
    }
}
