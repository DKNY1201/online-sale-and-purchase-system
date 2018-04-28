package edu.mum.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.onlineshopping.domain.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{
}
