package mum.edu.pm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	
	private String role;

	public Long getRoleId() {
		return id;
	}

	public void setRoleId(Long roleId) {
		this.id = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
