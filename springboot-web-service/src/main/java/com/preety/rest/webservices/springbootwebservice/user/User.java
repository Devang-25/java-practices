package com.preety.rest.webservices.springbootwebservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//static filtering
//@JsonIgnoreProperties(value={"id", "name"}) // ignore multiple field in response
@ApiModel(description="All details about user.")
@Entity
public class User {
//	@JsonIgnore //ignore one field in response
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should be minimum 2 characters")
	@ApiModelProperty(notes="Name should be minimum 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date can not be in past")
	private Date birthDate;
	
	@Temporal(TemporalType.DATE)
	private Date utilDate;

	@Temporal(TemporalType.TIME)
	private Date utilTime;
	
	@OneToMany(mappedBy="user")
	List<Post> posts;
	
	protected User() {}
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Date getUtilDate() {
		return utilDate;
	}
	public void setUtilDate(Date utilDate) {
		this.utilDate = utilDate;
	}
	public Date getUtilTime() {
		return utilTime;
	}
	public void setUtilTime(Date utilTime) {
		this.utilTime = utilTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	

}
