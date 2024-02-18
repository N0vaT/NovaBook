package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserSexDto {
    private User.Sex sex;
}
