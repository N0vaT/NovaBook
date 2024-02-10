package ru.nova.clientnovabook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FriendInvite {
    private Long inviteId;
    private Long userFrom;
    private Long userTo;
    private InviteStatus status;
    private LocalDateTime dateTime;
    public enum InviteStatus{
        WAITING, DENIED, ACCEPTED
    }
}
