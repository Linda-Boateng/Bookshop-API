package com.example.bookshop.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(
                cors -> cors.configurationSource(
                        request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of());
                            config.setAllowedMethods(
                                    List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
                            config.setAllowedHeaders(
                                    List.of("Content-Type", "Content-Disposition", "Authorization"));
                            return config;
                        }
                ))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request-> request
                .requestMatchers("/api/v1/public/**")
                .permitAll()
                .requestMatchers("/api/v1/user/**")
                .hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/api/v1/admin/**")
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
