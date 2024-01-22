package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserNameDto{
    private String firstName;
    private String lastName;
    private String patronymic;
}
