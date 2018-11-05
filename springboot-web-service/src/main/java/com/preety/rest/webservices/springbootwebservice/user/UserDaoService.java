package com.preety.rest.webservices.springbootwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users= new ArrayList<User>();
	
	private static  int userCount=3;
	
	static {
		users.add(new User(1, "Milie", new Date()));
		users.add(new User(2, "Jane", new Date()));
		users.add(new User(3, "Julie", new Date()));
	}
	
	//get all user
	public List<User> findAll(){
		return users;
	}
	//save user
	public User save(User user) {
		if(findOne(user.getId()) !=null) {
			throw new DuplicateException("User exists with id "+ user.getId());
		}
		if(user.getId() ==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	//get one user
	
	public User findOne(int id) {
		for(User u: users) {
			if(u.getId()== id) {
				return u;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> it= users.iterator();
		while(it.hasNext()) {
			User u= it.next();
			if(u.getId()== id) {
				it.remove();
				return u;
			}
		}
		return null;
	}

}
