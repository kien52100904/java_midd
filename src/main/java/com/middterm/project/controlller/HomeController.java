package com.middterm.project.controlller;

import com.middterm.project.model.Book;
import com.middterm.project.model.User;
import com.middterm.project.service.BookService;
import com.middterm.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private UserService userService;
    private BookService bookService;

    @GetMapping({"", "/", "/home"})
    public String getHome(@CookieValue(name="email", required = false) String email, Model model) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }

        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());

        return "index";
    }

    @GetMapping("/{id}")
    public String viewBookDetails(@CookieValue(name="email", required = false) String email, @PathVariable Integer id, Model model) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }

        Book book = bookService.getBookByid(id);
        model.addAttribute("book", book);
        return "details";
    }
}
