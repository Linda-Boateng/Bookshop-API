package com.example.bookshop.exception;

/**
 * This class handles not found exceptions.
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
