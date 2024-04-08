package com.example.bookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private String userId;
    private List<Book> books;
    private double totalAmount;
}
