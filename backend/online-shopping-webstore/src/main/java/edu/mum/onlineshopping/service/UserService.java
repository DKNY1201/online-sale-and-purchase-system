package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.User;

public interface UserService {

	User save(User user);
	
	User findByEmail(String email);

}
