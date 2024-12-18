package com.example.bookshop.controller;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.service.cartservice.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @Operation(
            summary = "Add to cart",
            description = "Add a book to the cart",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Book added to cart successfully"),
            })
    @PostMapping("/cart")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(cartService.addToCart(cartDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get cart",
            description = "Get the cart of a user",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
            })
    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable String userId) {
        return new ResponseEntity<>(cartService.getCart(userId),HttpStatus.OK);
    }

    @Operation(
            summary = "Delete cart",
            description = "Delete the cart of a user",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cart deleted successfully"),
            })
    @DeleteMapping("/cart/{userId}")
    public ResponseEntity<CartResponseDto> deleteCart(@PathVariable String userId) {
        return new ResponseEntity<>(cartService.deleteCart(userId),HttpStatus.OK);
    }
}
