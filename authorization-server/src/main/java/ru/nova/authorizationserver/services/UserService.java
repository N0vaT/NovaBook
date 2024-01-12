package ru.nova.authorizationserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nova.authorizationserver.model.Role;
import ru.nova.authorizationserver.model.User;
import ru.nova.authorizationserver.model.dto.RegistrationUserDTO;
import ru.nova.authorizationserver.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean saveUser(RegistrationUserDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            return false;
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        userRepository.save(user);
        return true;
    }
}
