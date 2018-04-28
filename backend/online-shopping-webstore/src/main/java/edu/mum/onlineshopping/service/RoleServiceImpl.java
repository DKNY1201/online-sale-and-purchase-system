package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Role;
import edu.mum.onlineshopping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAll() {
		return roleRepository.findAll();
	}

}
