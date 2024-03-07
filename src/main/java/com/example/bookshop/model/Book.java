package com.example.bookshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Book {
    @Id
   private String id;
   private String title;
   private String author;
   private int price;
}
