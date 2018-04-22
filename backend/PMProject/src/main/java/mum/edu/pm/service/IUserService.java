package mum.edu.pm.service;

import mum.edu.pm.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(long userId);

    void removeUser(User user);
}
