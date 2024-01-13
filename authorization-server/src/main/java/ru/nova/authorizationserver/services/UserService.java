package ru.nova.authorizationserver.services;

import ru.nova.authorizationserver.model.dto.RegistrationDto;

public interface UserService {
    public boolean saveUser(RegistrationDto registrationDto);
}
