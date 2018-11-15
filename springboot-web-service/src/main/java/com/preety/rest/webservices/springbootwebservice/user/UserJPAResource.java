package com.preety.rest.webservices.springbootwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService userservice;

	@Autowired
	private UserRepository repo;
	// retrieve all users
	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	//retrieve one user
	@GetMapping("/jpa/users/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
		
		Optional<User> user= repo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found with id "+id);
		}
		//Resource<User> userResource= new Resource(user.get());
		return user;
	}
	
	//create user
	//input - details of user
	// output- user created and uri
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User usercreated= repo.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usercreated.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//delete one user
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {	
		repo.deleteById(id);
	}
	
	//retrieve all posts of one user
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllUserPosts(@PathVariable int id) {
		
		Optional<User> user= repo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found with id "+id);
		}
		List<Post> posts= user.get().getPosts();
		return posts;
	}
	
}
