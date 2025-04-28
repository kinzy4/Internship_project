package com.internshipt.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for stateless JWT authentication
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()  // Allow public access to authentication endpoints
                .anyRequest().authenticated()  // Require authentication for all other requests
                .and()
                .sessionManagement()
                .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS);  // Disable session management since we're using stateless JWTs

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
