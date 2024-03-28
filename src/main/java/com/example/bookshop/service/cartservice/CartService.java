package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.model.Cart;

public interface CartService {
    CartResponseDto addToCart(CartDto cartDto);

    //Cart getCart(String userId) ;
}
