package com.middterm.project.repository;

import com.middterm.project.model.Book;
import com.middterm.project.model.CartItem;
import com.middterm.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    public List<CartItem> findByUser(User user);
    public CartItem findByUserIdAndBookId(Integer userId, Integer bookId);
}
