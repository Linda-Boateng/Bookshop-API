package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.exception.NotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Order;
import com.example.bookshop.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.bookshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository  bookRepository;
    private final OrderRepository orderRepository;

    @Override
    public BookResponseDto addBook(BookDto bookDto) {
        Optional<Book> bookExist = bookRepository.findByTitle(bookDto.getTitle());

  private final BookRepository bookRepository;
  private final MongoTemplate mongoTemplate;

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

    return BookResponseDto.builder().message("Book Added Successfully").book(createdBook).build();
  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public List<Book> searchBook(String query) {
    return bookRepository.findByTitleOrAuthor(query);
  }

  @Override
  public BookResponseDto deleteBook(String title) {
    Optional<Book> bookExist = bookRepository.findByTitle(title);
    if (bookExist.isEmpty()) throw new NotFoundException("Book already deleted");
    bookRepository.deleteByTitle(title);
    return BookResponseDto.builder().message("Book deleted successfully").build();
  }

  @Override
  public BookResponseDto editBook(BookDto bookDto) {
    Optional<Book> existingBook = bookRepository.findByTitle(bookDto.getTitle());

    @Override
    public List<Book> searchBook(String query) {
        return bookRepository.findByTitleOrAuthor(query);
    }

    @Override
    public List<Book> purchasedBooks(String userId, boolean isPaid) {
        List<Order> userOrders = orderRepository.findAllByUserIdAndPaid(userId,isPaid);
        System.out.println(userOrders);
        List<Book> purchasedBook = new ArrayList<>();
        for (Order order: userOrders){
            if (order.isPaid()) {
                purchasedBook.addAll(order.getBooks());
            }
        }
        return purchasedBook;
    }

    if (existingBook.isEmpty()) throw new NotFoundException("Book not found");
    Book  book = existingBook.get();
    book.setTitle(bookDto.getTitle());
    book.setAuthor(bookDto.getAuthor());
    book.setDescription(bookDto.getDescription());
    book.setPrice(bookDto.getPrice());
    Book updatedBook = bookRepository.save(book);
    return BookResponseDto.builder().message("Book updated successfully").book(updatedBook).build();
  }
}
