package mum.edu.pm.service;

import mum.edu.pm.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    Map<String, User> map = new HashMap<>();

    public Map<String, User> getAll() {
        User u1 = new User("Tuyen", "Ngo");
        User u2 = new User("Son", "Pham");
        map.put("1", u1);
        map.put("2", u2);
        return map;
    }
}
