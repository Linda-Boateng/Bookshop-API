package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.exception.NotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Cart;
import com.example.bookshop.repository.CartRepository;
import java.util.List;
import java.util.Optional;
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

        Cart existingCart = mongoTemplate.findOne(query, Cart.class);
        if (existingCart != null) {
            List<Book> updatedBooks = existingCart.getBooks();
            updatedBooks.addAll(cartDto.getBooks());

            Update update = Update.update("books", updatedBooks);
            mongoTemplate.updateFirst(query, update, Cart.class);

            return CartResponseDto.builder().message("Books added to cart successfully").build();
        }else{
           existingCart = new Cart();
           existingCart.setUserId(cartDto.getUserId());
           existingCart.setBooks(cartDto.getBooks());
           Cart createdCart = mongoTemplate.save(existingCart);

            return CartResponseDto.builder().cart(createdCart).message("Cart created and books added successfully").build();
        }
    }

    @Override
    public CartResponseDto getCart(String userId) {
        Optional<Cart> cartExist = cartRepository.findByUserId(userId);
    if (cartExist.isEmpty()) throw new NotFoundException("You have not added to your cart");
    return CartResponseDto.builder().cart(cartExist.get()).build();
    }

    @Override
    public CartResponseDto deleteCart(String userId) throws IllegalAccessException {
        Optional<Cart> cartExist = cartRepository.findByUserId(userId);
        if(cartExist.isEmpty()) throw new IllegalAccessException("You have no item in your cart");
        cartRepository.deleteByUserId(userId);
        return CartResponseDto.builder().message("Cart deleted successfully").build();
    }
}
