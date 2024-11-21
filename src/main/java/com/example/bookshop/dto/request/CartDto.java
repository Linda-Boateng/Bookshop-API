package com.example.bookshop.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private String userId;
    private String bookId;
}
