package com.preety.bookcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.preety")
@SpringBootApplication
public class BookCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}
}

/*
 * You are given a catalog of books, which have following attributes.
         Name
         Author
         Publisher
         Publish year
         Category
         Price
         Count (sold)
Implement following APIs on top of this catalog
         addBookToCatalog(Book)
         searchBook(by partial book name/author)
         getMostSoldBooks(by author name/category, limit)
Expectations:
         Maintain DB on memory
         Code should be readable. 
         Design, handle naming convention,
         handle exceptions & should be running
 */
