package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository  bookRepository;
    @Override
    public BookResponseDto addBook(BookDto bookDto) {
        Optional<Book> bookExist = bookRepository.findByTitle(bookDto.getTitle());
    if (bookExist.isPresent()) throw new DuplicateException("Book already is added");

    Book book =
        Book.builder()
            .title(bookDto.getTitle())
            .author(bookDto.getAuthor())
            .description(bookDto.getDescription())
            .price(bookDto.getPrice())
            .build();

   Book createdBook = bookRepository.save(book);

   System.out.println("User is logged in: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

    return BookResponseDto.builder()
        .id(createdBook.getId())
        .title(createdBook.getTitle())
        .author(createdBook.getAuthor())
        .build();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
