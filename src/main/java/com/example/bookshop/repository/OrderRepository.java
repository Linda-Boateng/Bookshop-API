package com.example.bookshop.repository;

import com.example.bookshop.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {}
