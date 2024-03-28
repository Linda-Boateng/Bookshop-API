package com.example.bookshop.service.userservice;

import com.example.bookshop.dto.request.UserDto;
import com.example.bookshop.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserDto request);
}
