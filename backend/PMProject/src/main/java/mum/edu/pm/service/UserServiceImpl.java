package mum.edu.pm.service;

import mum.edu.pm.entity.User;
import mum.edu.pm.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }
}
