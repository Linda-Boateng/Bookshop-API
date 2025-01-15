package com.example.bookshop.service.orderservice;

import com.example.bookshop.dto.request.OrderDto;
import com.example.bookshop.dto.request.PaymentDto;
import com.example.bookshop.dto.response.OrderResponseDto;
import com.example.bookshop.dto.response.PaymentResponseDto;
import com.example.bookshop.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto checkOut(OrderDto orderDto);
    PaymentResponseDto orderPayment(PaymentDto paymentDto);

   List<Order> getAllOrders();
}
