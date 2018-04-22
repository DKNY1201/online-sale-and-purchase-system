package mum.edu.pm.repository;

import org.springframework.data.repository.CrudRepository;

import mum.edu.pm.entity.User;

public interface UserRepositoryImpl extends CrudRepository<User, Long> {
	
}
