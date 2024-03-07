package com.example.bookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  private String id;
  private String username;
  private String password;
  private String email;
  private Enum<ROLE> role;
}
