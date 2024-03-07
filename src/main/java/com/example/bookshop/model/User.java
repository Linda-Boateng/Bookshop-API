package com.example.bookshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class User {
    @Id
    String id;
    String username;
    String password;

}
