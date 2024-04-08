package com.example.bookshop.controller;

import com.example.bookshop.dto.request.OrderDto;
import com.example.bookshop.dto.response.OrderResponseDto;
import com.example.bookshop.service.orderservice.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> order(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.checkOut(orderDto), HttpStatus.CREATED);
    }
}
