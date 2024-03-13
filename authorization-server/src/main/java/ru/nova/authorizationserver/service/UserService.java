package ru.nova.authorizationserver.service;

import ru.nova.authorizationserver.model.dto.RegistrationDto;

public interface UserService {
    boolean saveUser(RegistrationDto registrationDto);
    long getUserIdByEmail(String email);
}
