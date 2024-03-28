package com.example.bookshop.service.userservice;

import static com.example.bookshop.model.ROLE.*;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserResponseDto registerUser(UserDto request) {
    Optional<User> userExists = userRepository.findByEmail(request.getEmail());

    if (userExists.isPresent()) throw new DuplicateException("User Already Registered");
    User user = User.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(USER.name())
            .build();

    User created = userRepository.save(user);

    return UserResponseDto.builder()
        .id(created.getId())
        .email(created.getEmail())
        .build();
  }
}
