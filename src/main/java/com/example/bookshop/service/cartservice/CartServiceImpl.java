package com.example.bookshop.service.cartservice;

import com.example.bookshop.dto.request.CartDto;
import com.example.bookshop.dto.response.CartResponseDto;
import com.example.bookshop.exception.NotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Cart;
import com.example.bookshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

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
           existingCart.setId(cartDto.getUserId());
           existingCart.setBooks(cartDto.getBooks());
            mongoTemplate.save(existingCart);

            return CartResponseDto.builder().message("Cart created and books added successfully").build();
        }
    }

    @Override
    public CartResponseDto getCart(String userId) {
        List<Cart> cartExist = cartRepository.findAllByUserId(userId);
    if (cartExist.isEmpty()) throw new NotFoundException("You have not added to your cart");
    return CartResponseDto.builder().cartList(cartExist).build();
    }

    @Override
    public CartResponseDto deleteCart(String userId) throws IllegalAccessException {
        List<Cart> cartExist = cartRepository.findAllByUserId(userId);
        if(cartExist.isEmpty()) throw new IllegalAccessException("You have no item in your cart");
        cartRepository.deleteByUserId(userId);
        return CartResponseDto.builder().message("Cart deleted successfully").build();
    }
}
