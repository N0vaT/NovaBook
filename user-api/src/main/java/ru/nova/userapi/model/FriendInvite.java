package ru.nova.userapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nb_friend_invites")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FriendInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Long inviteId;
    @ManyToOne()
    @JoinColumn(name = "user_from")
    private User userFrom;
    @ManyToOne()
    @JoinColumn(name = "user_to")
    private User userTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InviteStatus status;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    public enum InviteStatus{
        WAITING, DENIED, ACCEPTED
    }
}
