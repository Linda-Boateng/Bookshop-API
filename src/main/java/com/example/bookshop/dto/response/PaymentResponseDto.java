package com.example.bookshop.dto.response;

import com.example.bookshop.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto {
   private String message;
    private Order orders;
}
