package com.flabum.squidzbackend.iam.infrastructure.configuration;


import com.flabum.squidzbackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final BCryptHashingService bCryptHashingService;

    public WebSecurityConfiguration(BCryptHashingService bCryptHashingService) {
        this.bCryptHashingService = bCryptHashingService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bCryptHashingService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/sign-up").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf().disable(); // Deshabilitar CSRF solo para pruebas

        return http.build();
    }

}