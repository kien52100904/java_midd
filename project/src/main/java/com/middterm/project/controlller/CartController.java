package com.middterm.project.controlller;

import com.middterm.project.model.Book;
import com.middterm.project.model.CartItem;
import com.middterm.project.model.User;
import com.middterm.project.service.BookService;
import com.middterm.project.service.CartService;
import com.middterm.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private UserService userService;
    private CartService cartService;
    private BookService bookService;

    @GetMapping({"", "/"})
    public String getCart(@CookieValue(name="email", required = false) String email, Model model) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }

        List<CartItem> cartItems = cartService.getCartItems(userService.findUserByEmail(email));
        model.addAttribute("cartItems", cartItems);

        double total = 0;

        for (CartItem cartItem : cartItems) {
            total += cartItem.getBook().getPrice() * cartItem.getQuantity();
        }

        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addCartItem(@CookieValue(name = "email", required = false) String email, @PathVariable Integer id, @RequestParam(name = "quantity", defaultValue = "1") int quantity) {
        User user = userService.findUserByEmail(email);
        Book book = bookService.getBookByid(id);
        cartService.addCartItem(book, user, quantity);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Integer id) {
        cartService.deleteCartItem(id);

        return "redirect:/cart";
    }
}
