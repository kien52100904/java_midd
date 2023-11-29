package com.middterm.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String author;
	private double price; 
	private String description;

	public Book(String title, String author, double price, String description) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.description = description;
	}
}
