package com.example.bookshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
}
