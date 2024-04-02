package com.example.bookshop.dto.response;

import com.example.bookshop.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BookResponseDto {
    private String message;
    private Book book;

}
