package com.example.bookshop.service.userservice;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserResponseDto registerUser(UserDto request) {
        Optional<User> userExists;
        userExists = userRepository.findByEmail(request.getEmail());

        if (userExists.isPresent()) throw new DuplicateException("User Already Registered");
        return null;
    }
}
