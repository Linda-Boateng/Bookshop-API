package com.example.bookshop.repository;

import com.example.bookshop.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findAllByUserIdAndPaid(String userId,boolean isPaid);
}
