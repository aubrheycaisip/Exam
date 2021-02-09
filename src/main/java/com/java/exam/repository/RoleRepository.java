package com.java.exam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.java.exam.enums.RoleName;
import com.java.exam.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	Optional<Role> findByAuthority(RoleName name);

}
