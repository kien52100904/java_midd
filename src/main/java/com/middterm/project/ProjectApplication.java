package com.middterm.project;

import com.middterm.project.model.Book;
import com.middterm.project.model.Role;
import com.middterm.project.service.BookService;
import com.middterm.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class ProjectApplication {
    private UserService userService;
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(){
        return args -> {
//            addUsers();
//            addBooks();

        };
    }

    private void addUsers() {
        userService.register("phamhoangtrungkien2411@gmail.com", "kien@2003", "Phạm Hoàng Trung Kiên", "0123456789");
        userService.setRole("phamhoangtrungkien2411@gmail.com", Role.ROLE_ADMIN);
    }

    private void addBooks() {
        bookService.insertBook(new Book("The Great Gatsby", "F. Scott Fitgerald", 19.99, "A classic American novel set in the Roaring Twenties, exploring themes of wealth, excess, and the American Dream"));
        bookService.insertBook(new Book("To Kill a Mockingbird", "Harper Lee", 29.99, "A powerful novel addressing issues of racial injustice, moral growth, and compassion in the American South during the 1930s"));
        bookService.insertBook(new Book("1984", "George Orwell", 39.99, "A dystopian novel depicting a totalitarian society, surveillance, and the struggle for truth and individuality."));
    }
}
