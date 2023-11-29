package com.middterm.project.service;

import com.middterm.project.model.Book;
import com.middterm.project.model.CartItem;
import com.middterm.project.model.User;
import com.middterm.project.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private CartItemRepository cartItemRepository;
    private BookService bookService;

    public List<CartItem> getCartItems(User user) {

        return cartItemRepository.findByUser(user);
    }

    public void addCartItem(Book book, User user, int quantity) {
        CartItem cartItem = cartItemRepository.findByUserIdAndBookId(user.getId(), book.getId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setBook(book);
        }

        cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }
}
