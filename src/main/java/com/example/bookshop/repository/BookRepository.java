package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,String> {
    Optional<Book> findByTitle(String title);

    @Query("{$or:[{'title': {$regex : ?0, $options: 'i'}}, {'author': {$regex : ?0, $options: 'i'}}]}")
    List<Book> findByTitleOrAuthor(String query);

    void deleteByTitle(String title);
}
