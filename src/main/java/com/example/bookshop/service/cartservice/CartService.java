package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;

public interface CartService {
    CartResponseDto addToCart(CartDto cartDto);

    CartResponseDto getCart(String userId);
    CartResponseDto deleteCart(String userId) throws IllegalAccessException;
}
