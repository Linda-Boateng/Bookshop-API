package com.example.bookshop.repository;

import com.example.bookshop.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart,String> {

    Optional<Cart> findByUserId(String userId);

    void deleteByUserId(String userId);
}
