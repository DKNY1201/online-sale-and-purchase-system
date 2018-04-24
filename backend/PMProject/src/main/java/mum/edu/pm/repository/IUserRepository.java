package mum.edu.pm.repository;

import mum.edu.pm.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {

}
