package com.example.bookshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFailureDto {
    String message;
    String path;
}
