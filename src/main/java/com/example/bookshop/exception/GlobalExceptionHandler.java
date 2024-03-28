package com.example.bookshop.exception;


import com.example.bookshop.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(value = {DuplicateException.class})
    ResponseEntity<ErrorResponseDto> handleInternalServerError(
            DuplicateException exception, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(
                new ErrorResponseDto(httpServletRequest.getRequestURI(), exception.getMessage()),
                HttpStatus.CONFLICT);
    }
}
