package ru.nova.clientnovabook.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.annotation.RequestScope;
import ru.nova.clientnovabook.service.RestUserService;
import ru.nova.clientnovabook.service.UserService;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(
//                        authorizeRequests -> authorizeRequests
//                                .mvcMatchers("/", "/css/**","/js/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(
//                        oauth2Login ->
//                                oauth2Login.loginPage("/oauth2/authorization/nb-client-oidc"))
//                .oauth2Client(withDefaults());
////                .logout(withDefaults());
//        return http.build();
//    }

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final LogoutHandler logoutService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .mvcMatchers("/", "/css/**","/js/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2Login ->
                                oauth2Login.loginPage("/oauth2/authorization/nb-client-oidc")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                                .addLogoutHandler(logoutService)
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
//                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");

        return oidcLogoutSuccessHandler;
    }
    @Bean
    @RequestScope
    public UserService userService(
            OAuth2AuthorizedClientService clientService) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String accessToken = null;

        if (authentication.getClass()
                .isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken =
                    (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId =
                    oauthToken.getAuthorizedClientRegistrationId();
            if (clientRegistrationId.equals("nb-client-oidc")) {
                OAuth2AuthorizedClient client =
                        clientService.loadAuthorizedClient(
                                clientRegistrationId, oauthToken.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        System.out.println(accessToken);
        return new RestUserService(accessToken);
    }

}
