package ru.nova.authorizationserver.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nova.authorizationserver.model.User;
import ru.nova.authorizationserver.repository.UserRepository;
import ru.nova.authorizationserver.security.SecurityUser;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        System.out.println(user);
        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username - " + username + " not found"));
    }

//    @Bean
//    public ApplicationRunner dataLoader(
//            UserRepository repo, PasswordEncoder encoder) {
//        return args -> {
//            repo.save(
//                    new ru.nova.authorizationserver.model.User(1L,"admin", encoder.encode("password"), new HashSet<>()));
//        };
//    }
}