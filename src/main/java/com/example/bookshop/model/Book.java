package com.example.bookshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Book {
    @Id
    String id;
    String title;
    String author;
    int price;
}
