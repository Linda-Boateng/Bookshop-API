package com.example.bookshop.service.bookservice;

import com.example.bookshop.dto.request.BookDto;
import com.example.bookshop.dto.response.BookResponseDto;
import com.example.bookshop.model.Book;
import java.util.List;

public interface BookService {
  /**
   * Adds a new book to the system.
   *
   * @param bookDto the data transfer object containing the details of the book to be added
   * @return a response data transfer object containing the details of the added book
   */
  BookResponseDto addBook(BookDto bookDto);

  /**
   * Retrieves all books from the system.
   *
   * @return a list of all books
   */
  List<Book> getAllBooks();

  /**
   * Searches for books based on a query string.
   *
   * @param query the search query string
   * @return a list of books that match the search query
   */
  List<Book> searchBook(String query);

  /**
   * Retrieves a list of books purchased by a user.
   *
   * @param userId the ID of the user
   * @param isPaid a boolean indicating whether to retrieve paid books
   * @return a list of books purchased by the user
   */
  List<Book> purchasedBooks(String userId, boolean isPaid);

  /**
   * Deletes a book from the system.
   *
   * @param bookId the ID of the book to be deleted
   * @return a response data transfer object containing the details of the deleted book
   */
  BookResponseDto deleteBook(String bookId);

  /**
   * Edits the details of an existing book.
   *
   * @param bookDto the data transfer object containing the updated details of the book
   * @return a response data transfer object containing the details of the edited book
   */
  BookResponseDto editBook(BookDto bookDto);
}
