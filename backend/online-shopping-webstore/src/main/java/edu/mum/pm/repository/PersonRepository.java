package edu.mum.pm.repository;


import edu.mum.pm.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findByEmail(String email);

    public List<Customer> findByEnable(boolean enable);

}
