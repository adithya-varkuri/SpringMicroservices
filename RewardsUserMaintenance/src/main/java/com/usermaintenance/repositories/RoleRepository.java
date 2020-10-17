package com.usermaintenance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermaintenance.entities.Role;

@Repository("roleRepository")
public interface RoleRepository extends  JpaRepository<Role, Integer> {
	
	List<Role> findByRole(String role);

}
