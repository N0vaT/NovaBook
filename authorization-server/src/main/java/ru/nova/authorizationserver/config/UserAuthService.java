package ru.nova.authorizationserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.nova.authorizationserver.repository.UserRepository;

import java.util.HashSet;

@RequiredArgsConstructor
@Configuration
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Поиск юзера " + username);
        return repository.findByUsername(username)
                .map(user-> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles()
                )).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
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