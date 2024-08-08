package com.flabum.squidzbackend.iam.infrastructure.authorization.sfs.configuration;


import com.flabum.squidzbackend.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.flabum.squidzbackend.iam.infrastructure.authorization.sfs.service.UserDetailsServiceImpl;
import com.flabum.squidzbackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.BearerTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final BCryptHashingService bCryptHashingService;
    private final UserDetailsServiceImpl userDetailsService;
    private final BearerTokenService bearerTokenService;


    public WebSecurityConfiguration(BCryptHashingService bCryptHashingService
    , UserDetailsServiceImpl userDetailsService, BearerTokenService bearerTokenService) {
        this.userDetailsService = userDetailsService;
        this.bCryptHashingService = bCryptHashingService;
        this.bearerTokenService = bearerTokenService;
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(bearerTokenService, userDetailsService);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptHashingService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bCryptHashingService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/users/sign-in",
                            "/api/v1/users/sign-up",
                            "/api/v1/users/recover-account-email",
                            "/api/v1/users/verify-account",
                            "/swagger-ui/index.html#/Users/signIn",
                            "/swagger-ui/index.html#/Users/signUp",
                            "/swagger-ui/index.html#/Users/recoverAccountEmail",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/swagger-ui.html",
                            "/webjars/**").permitAll();
                    auth.requestMatchers("/api/v1/users/update-password", "/api/v1/users/update-user-data", "/api/v1/roles").hasRole("USER");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(customizer -> {
                    customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers(customizer -> {
                    customizer.contentSecurityPolicy("default-src 'self'; script-src 'self' https://apis.google.com");
                    customizer.addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "default-src 'self'; script-src 'self' https://apis.google.com"));
                })
                .build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}