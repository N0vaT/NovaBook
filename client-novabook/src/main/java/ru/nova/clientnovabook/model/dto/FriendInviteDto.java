package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.FriendInvite;
import ru.nova.clientnovabook.model.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FriendInviteDto {
    private Long inviteId;
    private Long userFrom;
    private Long userTo;
    private FriendInvite.InviteStatus status;
    private LocalDateTime dateTime;
}