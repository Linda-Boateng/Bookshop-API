package com.example.bookshop.repository;

import com.example.bookshop.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {}
