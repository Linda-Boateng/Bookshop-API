package com.example.bookshop.repository;

import com.example.bookshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
