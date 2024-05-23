package com.example.bookshop.exception;

public class IncompletePaymentException extends  RuntimeException{
    public IncompletePaymentException(String message){
        super(message);
    }
}
