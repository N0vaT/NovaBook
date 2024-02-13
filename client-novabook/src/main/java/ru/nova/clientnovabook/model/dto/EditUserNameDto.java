package ru.nova.clientnovabook.model.dto;

import jakarta.validation.constraints.Size;
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
    @Size(max = 20, message = "Имя должно содержать меньше 20 символов")
    private String firstName;
    @Size(max = 20, message = "Фамилия должно содержать меньше 20 символов")
    private String lastName;
    @Size(max = 20, message = "Отчество должно содержать меньше 20 символов")
    private String patronymic;
}
