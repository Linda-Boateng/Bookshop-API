package com.example.bookshop.service.userservice;

import static com.example.bookshop.model.enums.ROLE.*;
import static com.example.bookshop.util.ConstantStrings.USER_ALREADY_EXIST;

import com.example.bookshop.dto.request.UserDto;
import com.example.bookshop.dto.response.UserResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerUser(UserDto request) {
    User userExists = userRepository.findByEmail(request.getEmail());

    if (userExists != null) throw new DuplicateException(USER_ALREADY_EXIST);
    User user =
        User.builder()
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .role(
                (request.getRole() != null && (request.getRole().equals(ADMIN.name())))
                    ? ADMIN.name()
                    : USER.name())
            .build();

    User created = userRepository.save(user);

      UserResponseDto.builder().id(created.getId()).email(created.getEmail()).build();
  }
}
