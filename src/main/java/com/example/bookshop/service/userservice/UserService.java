package com.example.bookshop.service.userservice;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserDto request);
}
