package com.example.bookshop.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String accessToken;
    private String name;
    private String username;
}
