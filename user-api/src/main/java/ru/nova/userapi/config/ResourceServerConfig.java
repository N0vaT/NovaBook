package ru.nova.userapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.opaque.issuer-uri}")
    private String issuerUri;
    @Value("${spring.security.oauth2.resourceserver.opaque.client}")
    private String client;
    @Value("${spring.security.oauth2.resourceserver.opaque.secret}")
    private String secret;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt().jwkSetUri("http://127.0.0.1:9000/oauth2/jwks"));
                .oauth2ResourceServer((oauth2) -> oauth2
                        .opaqueToken()
                        .introspectionUri(issuerUri)
                        .introspectionClientCredentials(client, secret)
                );
        return http.build();
    }
}
