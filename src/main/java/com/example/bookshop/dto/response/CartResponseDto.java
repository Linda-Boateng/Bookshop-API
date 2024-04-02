package com.example.bookshop.dto.response;

import com.example.bookshop.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDto {
    private String message;
    private List<Cart> cartList;
}
