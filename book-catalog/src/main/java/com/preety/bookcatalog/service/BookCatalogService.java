package com.preety.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.preety.bookcatalog.domain.Book;
import com.preety.bookcatalog.repository.BookRepository;

@Component
public class BookCatalogService {
	
	@Autowired
	private BookRepository repo;

	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}


	public Book getBookWithNameOrAuthorPattern(String key) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Book> getMostSoldBooks(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Book> getBooksWithSpecification(Specification<Book> spec) {
		// TODO Auto-generated method stub
		return repo.findAll(spec);
	}

}
