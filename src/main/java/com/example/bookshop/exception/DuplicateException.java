package com.example.bookshop.exception;

/**
 * This class handles duplicate exceptions.
 */
public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
