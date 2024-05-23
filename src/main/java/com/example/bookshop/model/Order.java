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
    private double totalAmount;
    @Builder.Default
    private boolean paid = false;
    private List<Book> books;
}
