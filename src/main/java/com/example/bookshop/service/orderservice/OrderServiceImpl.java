package com.example.bookshop.service.orderservice;

import com.example.bookshop.dto.request.OrderDto;
import com.example.bookshop.dto.request.PaymentDto;
import com.example.bookshop.dto.response.OrderResponseDto;
import com.example.bookshop.dto.response.PaymentResponseDto;
import com.example.bookshop.exception.IncompletePaymentException;
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

    @Override
    public PaymentResponseDto orderPayment(PaymentDto paymentDto) {
    Optional<Order> existingOrder = orderRepository.findById(paymentDto.getId());
    if (existingOrder.isEmpty()) throw new NotFoundException("Order not found");
    Order userOrder = existingOrder.get();
    double amount = userOrder.getTotalAmount();
    if (paymentDto.getTotalAmount() != amount)
      throw new IncompletePaymentException("Enter full Amount please");
    userOrder.setPaid(true);
    orderRepository.save(userOrder);
    cartRepository.deleteByUserId(paymentDto.getUserId());
    return PaymentResponseDto.builder().message("Payment made successfully").build();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private double calculatePrice(List<Book> books){
        double totalPrice = 0;
        for (Book book: books){
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }
}
