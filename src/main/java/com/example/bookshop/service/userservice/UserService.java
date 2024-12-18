package com.example.bookshop.service.userservice;

import com.example.bookshop.dto.request.UserDto;

public interface UserService {
    /**
     * This method is used to register a user
     * @param request the request object
     */
    void registerUser(UserDto request);
}
