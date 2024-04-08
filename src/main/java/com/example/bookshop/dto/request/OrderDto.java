package com.example.bookshop.dto.request;

import com.example.bookshop.model.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private String userId;
    private List<Book> books;
    private double totalAmount;
}
