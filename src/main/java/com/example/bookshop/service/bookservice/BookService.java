package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;

public interface BookService {
    BookResponseDto addBook(BookDto bookDto);
}
