package com.example.bookshop.repository;

import com.example.bookshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {}
