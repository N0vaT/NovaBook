package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.FriendInvite;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendInviteDto {
    private Long inviteId;
    private Long userFrom;
    private String nameFrom;
    private String avatarFrom;
    private Long userTo;
    private String nameTo;
    private String avatarTo;
    private FriendInvite.InviteStatus status;
    private LocalDateTime dateTime;
}