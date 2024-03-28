package com.example.bookshop.dto.request;

import com.example.bookshop.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private String userId;
    private List<Book> books;
}
