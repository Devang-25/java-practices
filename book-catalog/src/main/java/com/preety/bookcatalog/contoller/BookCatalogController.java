package com.preety.bookcatalog.contoller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.preety.bookcatalog.domain.Book;
import com.preety.bookcatalog.service.BookCatalogService;
import com.preety.bookcatalog.specification.BookSpecificationsBuilder;

@RestController
public class BookCatalogController {
	
	@Autowired
	private BookCatalogService service;
	//addBookToCatalog(Book)
	@PostMapping("/book")
	public void addBookToCatalog(@RequestBody Book book) {
		service.addBook(book);
	}
	
	// searchBook(by partial book name/author)
	@GetMapping("/book")
		public Book getBook(@RequestParam String nameOrAuthorKey) {
			Book book= service.getBookWithNameOrAuthorPattern(nameOrAuthorKey);
			return book;
		}
	
	
	// get books
	//getMostSoldBooks(by author name/category, limit)
	@GetMapping("/books")
	public List<Book> search(@RequestParam(value = "search") String search) {
        BookSpecificationsBuilder builder = new BookSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
         
        Specification<Book> spec = builder.build();
        return service.getBooksWithSpecification(spec);
    }

}
