package com.preety.bookcatalog.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.preety.bookcatalog.domain.Book;

public class BookSpecificationsBuilder {
    
    private final List<SearchCriteria> params;
 
    public BookSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public BookSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
    public Specification<Book> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Book>> specs = new ArrayList<Specification<Book>>();
        for (SearchCriteria param : params) {
            specs.add(new BookSpecification(param));
        }
 
        Specification<Book> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
