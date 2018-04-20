package mum.edu.pm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mum.edu.pm.entity.*;

@Service
public class UserService {
	Map<String, User> map = new HashMap<>();
	
	public Map<String, User> getAll() {
		User u1 = new User("Tuyen", "Ngo");
		User u2 = new User("Son","Pham");
		map.put("1", u1);
		map.put("2", u2);
		return map;
	}
}
