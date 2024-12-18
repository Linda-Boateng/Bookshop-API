package com.example.bookshop.exception;

/**
 * This class handles incomplete payment exceptions.
 */
public class IncompletePaymentException extends  RuntimeException{
    public IncompletePaymentException(String message){
        super(message);
    }
}
