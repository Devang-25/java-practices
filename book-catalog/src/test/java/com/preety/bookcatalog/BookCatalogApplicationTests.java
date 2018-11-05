//package com.preety.bookcatalog;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.domain.Specifications;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.preety.bookcatalog.domain.Book;
//import com.preety.bookcatalog.domain.Category;
//import com.preety.bookcatalog.repository.BookRepository;
//import com.preety.bookcatalog.specification.BookSpecification;
//import com.preety.bookcatalog.specification.SearchCriteria;
//import static org.hamcrest.Matchers.isIn;
//import static org.hamcrest.Matchers.not;
//import static org.junit.Assert.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BookCatalogApplicationTests {
//	
//	 @Autowired
//	    private BookRepository repository;
//	 
//	    private Book book1;
//	    private Book book2;
//	 
//	    @Before
//	    public void init() {
//	        book1 = new Book();
//	        book1.setName("humanity");
//	        book1.setAuthor("Russel");
//	        book1.setCategory(Category.Arts);
//	        book1.setPrice(100.0);
//	        book1.setPublisher("Orelly");
//	        book1.setPublishYear(2012);
//	        book1.setSoldCount(80);
//	        book1.setPrice(100.0);
//	        repository.save(book1);
//	 
//	        book2 = new Book();
//	        book2.setName("humanity");
//	        book2.setAuthor("Russel");
//	        book2.setCategory(Category.Arts);
//	        book2.setPrice(100.0);
//	        book2.setPublisher("Orelly");
//	        book2.setPublishYear(2012);
//	        book2.setSoldCount(120);
//	        book2.setPrice(100.0);
//	        repository.save(book2);
//	    }
//
////	@Test
//	public void contextLoads() {
//	}
//	
//	@Test
//	public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
//	    BookSpecification spec1 = 
//	      new BookSpecification(new SearchCriteria("soldCount", ">", "100"));
//	    BookSpecification spec2 = 
//	      new BookSpecification(new SearchCriteria("price", ":", "100.0"));
//	 
//	    List<Book> results = 
//	      repository.findAll(Specifications.where(spec1).and(spec2));
//	 
//	    assertThat(book2, isIn(results));
//	    assertThat(book1, not(isIn(results)));
//	}
//
//
//}
