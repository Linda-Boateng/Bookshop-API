package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,String> {
    Optional<Book> findByTitle(String title);
}
