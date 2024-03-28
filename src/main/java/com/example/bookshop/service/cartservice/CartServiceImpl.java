package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.model.Cart;
import com.example.bookshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final MongoTemplate mongoTemplate;
    private final CartRepository cartRepository;
    @Override
    public CartResponseDto addToCart(CartDto cartDto) {
        Query query = Query.query(Criteria.where("userId").is(cartDto.getUserId()));
        Update update =
                new Update().set("books", cartDto.getBooks());

        mongoTemplate.upsert(query,update,Cart.class);
        return  CartResponseDto.builder().message("Book added to cart successfully").build();
    }

   /* @Override
    public Cart getCart(String userId) {
        Optional<Cart> cartExist = cartRepository.findByUserId(userId);
        if (cartExist.isEmpty()) return null;
    }*/
}
