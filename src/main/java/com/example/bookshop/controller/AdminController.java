package com.example.bookshop.controller;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.service.bookservice.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class
AdminController {
  private final BookService bookService;

  @Operation(
          summary = "Register book",
          description = "Register a new book in the system",
          security = @SecurityRequirement(name = "JWT Bearer Token"),
          responses = {
                  @ApiResponse(responseCode = "200", description = "Book registered successfully"),
          })
  @PostMapping("/book")
  public ResponseEntity<BookResponseDto> registerBook(@RequestBody BookDto bookDto) {
    return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
  }

  @Operation(
          summary = "Delete book",
          description = "Delete a book from the system",
          security = @SecurityRequirement(name = "JWT Bearer Token"),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
          })
  @DeleteMapping("/book/{bookId}")
  public ResponseEntity<BookResponseDto> deleteBook(@PathVariable String bookId) {
    return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.NO_CONTENT);
  }

    @Operation(
            summary = "Edit book",
            description = "Edit a book in the system",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Book edited successfully"),
            })
  @PatchMapping("/book")
  public ResponseEntity<BookResponseDto> editBook(@RequestBody BookDto bookDto) {
    return new ResponseEntity<>(bookService.editBook(bookDto), HttpStatus.CREATED);
  }
}
