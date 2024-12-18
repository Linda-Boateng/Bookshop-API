package com.example.bookshop.controller;

import com.example.bookshop.model.Book;
import com.example.bookshop.service.bookservice.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(
            summary = "Search book",
            description = "Search for a book in the system",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of books that match the search query"),
            })
    @GetMapping("/book")
    public ResponseEntity<List<Book>> searchBook(@RequestParam("query") String query){
        return new ResponseEntity<>(bookService.searchBook(query),HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch Purchase book",
            description = "Fetch all user Purchased a books in the system",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of Books purchased by the user"),
            })
    @GetMapping("/books")
    public  ResponseEntity<List<Book>> getPurchasedBooks(@RequestParam String userId,
                                                         @RequestParam boolean isPaid){
        return new ResponseEntity<>(bookService.purchasedBooks(userId,isPaid
        ),HttpStatus.OK);
    }
}