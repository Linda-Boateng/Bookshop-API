package com.example.bookshop.config;

import com.example.bookshop.dto.response.LogoutResponseDto;
import com.example.bookshop.dto.response.UserFailureDto;
import com.example.bookshop.dto.response.UserResponseDto;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String CONTENT_TYPE = "application/json";
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http.cors(
            cors ->
                cors.configurationSource(
                    request -> {
                      CorsConfiguration config = new CorsConfiguration();
                      config.setAllowedOrigins(List.of());
                      config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
                      config.setAllowedHeaders(
                          List.of("Content-Type", "Content-Disposition", "Authorization"));
                      config.setAllowCredentials(true);
                      return config;
                    }))
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers("/api/v1/public/**")
                    .permitAll()
                    .requestMatchers("/api/v1/user/**")
                    .hasAnyAuthority("USER", "ADMIN")
                    .requestMatchers("/api/v1/admin/**")
                    .hasAnyAuthority("ADMIN")
                    .anyRequest()
                    .authenticated())
        .oauth2Login(Customizer.withDefaults())
        .formLogin(
            form ->
                form.loginProcessingUrl("/api/v1/public/login")
                    .usernameParameter("email")
                    .successHandler(this::authenticationSuccessHandler)
                    .failureHandler(this::authenticationFailureHandler)
                    .permitAll())
        .logout(
            logout ->
                logout
                    .logoutUrl("/api/v1/public/logout")
                    .logoutSuccessHandler(this::logoutSuccessHandler)
                    .permitAll())
        .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return new ProviderManager(
                Collections.singletonList(authenticationProvider()));
    }

    private void authenticationSuccessHandler(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setContentType(CONTENT_TYPE);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();

        Optional<User> userExists = userRepository.findByEmail(email);

        if (userExists.isEmpty()) throw new UsernameNotFoundException("User not found");

        UserResponseDto responseDto =
                UserResponseDto.builder()
                        .accessToken(request.getSession().getId())
                        .id(userExists.get().getId())
                        .username(userExists.get().getUsername())
                        .email(userExists.get().getEmail())
                        .role(String.valueOf(userExists.get().getRole()))
                        .isAccountEnabled(userExists.get().isEnabled())
                        .build();

        new ObjectMapper().writeValue(response.getOutputStream(), responseDto);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void authenticationFailureHandler(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(CONTENT_TYPE);
        new ObjectMapper()
                .writeValue(
                        response.getOutputStream(),
                        new UserFailureDto("Bad credentials", request.getRequestURI()));

    }
    private void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType(CONTENT_TYPE);
        new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), LogoutResponseDto.builder().message(
                "You have been logged out").build());

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
