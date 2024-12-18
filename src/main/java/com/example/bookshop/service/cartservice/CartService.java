package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;

public interface CartService {
    /**
     * Adds a new book to the cart.
     *
     * @param cartDto the data transfer object containing the details of the book to be added to the cart
     * @return a response data transfer object containing the details of the added book
     */
    CartResponseDto addToCart(CartDto cartDto);

    /**
     * Retrieves all books from the cart.
     *
     * @param userId the ID of the user
     * @return a response data transfer object containing the details of the books in the cart
     */
    CartResponseDto getCart(String userId);

    /**
     * Deletes a book from the cart.
     *
     * @param userId the ID of the user
     * @return a response data transfer object containing the details of the deleted book
     */
    CartResponseDto deleteCart(String userId);
}
