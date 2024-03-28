package com.example.bookshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookDto {
    private String title;
    private String author;
    private String description;
    private int price;
}
