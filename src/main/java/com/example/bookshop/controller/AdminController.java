package com.example.bookshop.controller;

import com.example.bookshop.dto.request.BookDto;

import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.service.bookservice.BookService;
import com.example.bookshop.service.orderservice.OrderService;
import com.example.bookshop.dto.request.UserDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.dto.response.UserResponseDto;
import com.example.bookshop.service.bookservice.BookService;
import com.example.bookshop.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> registerAdmin(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto),HttpStatus.CREATED);
    }

    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> registerBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }
  
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);

    @DeleteMapping("/book/{title}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable String title){
        return new ResponseEntity<>(bookService.deleteBook(title),HttpStatus.NO_CONTENT);
    }

    @PatchMapping                                                                                                                                       ("/book")
    public ResponseEntity<BookResponseDto> editBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.editBook(bookDto),HttpStatus.CREATED);

    }
}
