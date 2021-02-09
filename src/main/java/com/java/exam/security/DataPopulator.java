package com.java.exam.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.exam.enums.RoleName;
import com.java.exam.model.Role;
import com.java.exam.repository.RoleRepository;

@Service
public class DataPopulator implements InitializingBean {

	@Autowired
    private RoleRepository roleRepository;
	
	@Override
	@Transactional()
    public void afterPropertiesSet() throws Exception {
		System.out.println("entities " + roleRepository.count());
        if (roleRepository.count() == 0) {
            this.initRoles();
        }
        
    }
	private void initRoles() {
        List<Role> roles = new ArrayList<>();
        for (RoleName r : RoleName.values()) {
        	roles.add(new Role(r));
        }
        roleRepository.saveAll(roles);
    }
}
