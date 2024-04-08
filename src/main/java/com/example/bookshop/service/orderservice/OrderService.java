package com.example.bookshop.service.orderservice;

import com.example.bookshop.dto.request.OrderDto;
import com.example.bookshop.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto checkOut(OrderDto orderDto);

}
