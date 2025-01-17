package com.example.bookshop.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * User Model
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id private String id;
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String email;
  private String role;
  @DBRef @Builder.Default private List<Order> orders = new ArrayList<>();
  @DBRef @Builder.Default private List<Cart> cart = new ArrayList<>();
}
