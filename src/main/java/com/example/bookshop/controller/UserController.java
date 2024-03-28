package com.example.bookshop.controller;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserDto request){
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }

}
