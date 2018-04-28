package edu.mum.onlineshopping.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.onlineshopping.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public List<Person> findByEmail(String email);
	public List<Person> findByEnable(boolean enable);
	
}
