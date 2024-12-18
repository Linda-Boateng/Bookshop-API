package com.example.bookshop.controller;

import com.example.bookshop.model.Book;
import com.example.bookshop.service.bookservice.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/v1")
@RequiredArgsConstructor
public class PublicController {
    private final BookService bookService;

    @Operation(
            summary = "Get all books",
            description = "Get all books in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of all books in the system"),
            })
    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

}
