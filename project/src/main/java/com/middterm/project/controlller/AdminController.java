package com.middterm.project.controlller;

import com.middterm.project.model.Book;
import com.middterm.project.model.User;
import com.middterm.project.service.BookService;
import com.middterm.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private BookService bookService;
    private UserService userService;

    @GetMapping({"", "/" })
    public String getAdmin(@CookieValue(name="email", required = false) String email, Model model) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }

        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());

        return "admin";
    }

    @PostMapping("/add")
    public String insertBook(@ModelAttribute Book book, Model model) {
        bookService.insertBook(book);
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@CookieValue(name="email", required = false) String email, @PathVariable String id, Model model) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
        }

        Book book = bookService.getBookByid(Integer.parseInt(id));
        model.addAttribute("book", book);

        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable String id, @ModelAttribute Book book) {
        Book existingBook = bookService.getBookByid(Integer.parseInt(id));

        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setDescription(book.getDescription());

            bookService.updateBook(existingBook);
        }

        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id, Model model) {
        bookService.deleteBook(Integer.parseInt(id));

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "redirect:/admin";
    }
}
