package ru.nova.userapi.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.User;

import javax.persistence.*;
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
