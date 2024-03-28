package com.example.bookshop.controller;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.model.Book;
import com.example.bookshop.service.bookservice.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> registerBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(){
    return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }
}
