package com.careridetransport.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeRequests(authz -> authz
                        .requestMatchers("/**").permitAll() // Allow registration without authentication
                        .anyRequest().authenticated()) // All other requests should be authenticated
        // Further configurations as per your security requirements
        ;
        return http.build();
    }

    // Additional security configurations go here
}
