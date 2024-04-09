package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.model.Book;
import java.util.List;

public interface BookService {
    BookResponseDto addBook(BookDto bookDto);
    List<Book> getAllBooks();

    List<Book> searchBook(String query);

    List<Book> purchasedBooks(String userId, boolean isPaid);
}
