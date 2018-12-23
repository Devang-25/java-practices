package com.preety.rest.webservices.springbootwebservice.book;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Book {
//	@JsonIgnore //ignore one field in response
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should be minimum 2 characters")
	@ApiModelProperty(notes="Name should be minimum 2 characters")
	private String name;

	@Temporal(TemporalType.DATE)
	private Date publishDate;

	
	@ManyToMany(mappedBy = "books")
    private List<Author> authors;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	
	
}

