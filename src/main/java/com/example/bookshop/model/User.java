package com.example.bookshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class User {
  @Id
  private String id;
  private String username;
  private String password;
  private Enum<ROLE> role;
}
