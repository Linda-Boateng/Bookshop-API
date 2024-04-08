package com.example.bookshop.service.orderservice;

import com.example.bookshop.dto.request.OrderDto;
import com.example.bookshop.dto.response.OrderResponseDto;
import com.example.bookshop.exception.NotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Cart;
import com.example.bookshop.model.Order;
import com.example.bookshop.repository.CartRepository;
import com.example.bookshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Override
    public OrderResponseDto checkOut(OrderDto orderDto) {
        Optional<Cart> existingCart = cartRepository.findByUserId(orderDto.getUserId());
        if(existingCart.isEmpty()) throw new NotFoundException("You have not added to your cart");
        Cart userCart = existingCart.get();
        double totalPrice = calculatePrice(userCart.getBooks());
        Order order = Order.builder()
                .userId(userCart.getUserId())
                .books(userCart.getBooks())
                .totalAmount(totalPrice).build();
        Order createdOrder = orderRepository.save(order);
        return OrderResponseDto.builder().message("Checkout completed proceed to payment").order(createdOrder).build();
    }


    private double calculatePrice(List<Book> books){
        double totalPrice = 0;
        for (Book book: books){
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }
}
