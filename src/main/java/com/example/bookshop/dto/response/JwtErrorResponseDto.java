package com.example.bookshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class JwtErrorResponseDto {
    private String message;
    private String error;
}
