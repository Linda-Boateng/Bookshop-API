package com.example.bookshop.model;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Cart Model
 */
@Data
@RequiredArgsConstructor
public class Cart {

  @Id
  private String id;
  private String userId;

  @DBRef
  private List<Book> books;
}
