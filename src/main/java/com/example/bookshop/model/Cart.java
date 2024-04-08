package com.example.bookshop.model;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Cart {

  @Id
  private String id;
  private String userId;
  private List<Book> books;
}
