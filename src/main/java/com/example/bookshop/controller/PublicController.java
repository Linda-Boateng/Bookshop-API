package com.example.bookshop.controller;

import com.example.bookshop.model.Book;
import com.example.bookshop.service.bookservice.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

}
