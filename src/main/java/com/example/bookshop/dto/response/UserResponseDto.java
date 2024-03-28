package com.example.bookshop.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private String accessToken;
    private String role;
    private boolean isAccountEnabled;
}
