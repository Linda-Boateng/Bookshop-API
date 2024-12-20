package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.exception.DuplicateException;
import com.example.bookshop.exception.NotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Order;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final OrderRepository orderRepository;


  /**
  *{@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

    /**
     * {@inheritDoc}
     */
  @Override
  public List<Book> searchBook(String query) {
    return bookRepository.findByTitleOrAuthor(query);
  }

    /**
     * {@inheritDoc}
     */
  @Override
  public BookResponseDto deleteBook(String bookId) {
    Optional<Book> bookExist = bookRepository.findById(bookId);
    if (bookExist.isEmpty()) throw new NotFoundException("Book already deleted");
    bookRepository.deleteById(bookId);
    return BookResponseDto.builder().message("Book deleted successfully").build();
  }

    /**
     * {@inheritDoc}
     */
  @Override
  public BookResponseDto editBook(BookDto bookDto) {
    Optional<Book> existingBook = bookRepository.findByTitle(bookDto.getTitle());
    if (existingBook.isEmpty()) throw new NotFoundException("Book not found");
    Book book = existingBook.get();
    book.setTitle(bookDto.getTitle());
    book.setAuthor(bookDto.getAuthor());
    book.setDescription(bookDto.getDescription());
    book.setPrice(bookDto.getPrice());
    Book updatedBook = bookRepository.save(book);
    return BookResponseDto.builder().message("Book updated successfully").book(updatedBook).build();
  }

    /**
     * {@inheritDoc}
     */
  @Override
  public List<Book> purchasedBooks(String userId, boolean isPaid) {
    List<Order> userOrders = orderRepository.findAllByUserIdAndPaid(userId, isPaid);
    List<Book> purchasedBook = new ArrayList<>();
    for (Order order : userOrders) {
      if (order.isPaid()) {
        purchasedBook.addAll(order.getBooks());
      }
    }
    return purchasedBook;
  }
}
