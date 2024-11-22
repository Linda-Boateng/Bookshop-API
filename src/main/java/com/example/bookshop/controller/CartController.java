package com.example.bookshop.controller;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.service.cartservice.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping("/cart")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(cartService.addToCart(cartDto), HttpStatus.CREATED);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable String userId) {
        return new ResponseEntity<>(cartService.getCart(userId),HttpStatus.OK);
    }

    @DeleteMapping("/cart/{userId}")
    public ResponseEntity<CartResponseDto> deleteCart(@PathVariable String userId) {
        return new ResponseEntity<>(cartService.deleteCart(userId),HttpStatus.OK);
    }
}
