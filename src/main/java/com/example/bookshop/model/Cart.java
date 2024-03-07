package com.example.bookshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Cart {
    @Id
    String id;
    String userId;
    List<Book> books;
}
