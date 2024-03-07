package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {}
