package ru.nova.authorizationserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
}
