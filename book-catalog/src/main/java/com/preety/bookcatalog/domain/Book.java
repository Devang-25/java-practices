package com.preety.bookcatalog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	private String name;
    private String author;
    private String publisher;
    private int publishYear;
    private Category category;
    private double price;
    private int soldCount;
	public Book(String name, String author, String publisher, int publishYear, Category category, double price,
			int soldCount) {
		super();
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.publishYear = publishYear;
		this.category = category;
		this.price = price;
		this.soldCount = soldCount;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSoldCount() {
		return soldCount;
	}
	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", publisher=" + publisher + ", publishYear=" + publishYear
				+ ", category=" + category + ", price=" + price + ", soldCount=" + soldCount + "]";
	}
    
    

}


