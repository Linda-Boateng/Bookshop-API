package com.example.bookshop.controller;

import com.example.bookshop.dto.request.UserDto;
import com.example.bookshop.dto.response.UserResponseDto;
import com.example.bookshop.model.Book;
import com.example.bookshop.service.bookservice.BookService;
import com.example.bookshop.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;
    private final BookService bookService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserDto request){
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

}
