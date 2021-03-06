package com.preety.rest.webservices.springbootwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userservice;
	
	@Autowired
	private UserRepository repo;
	// retrieve all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	//retrieve one user
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		
		User user= userservice.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	//create user
	//input - details of user
	// output- user created and uri
	@PostMapping("users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User usercreated= userservice.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usercreated.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//delete one user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable int id) {
		
		User user= userservice.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		return ResponseEntity.noContent().build();
	}
}
