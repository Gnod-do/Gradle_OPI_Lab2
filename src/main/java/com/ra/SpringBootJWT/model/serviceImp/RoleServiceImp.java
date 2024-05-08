package com.ra.SpringBootJWT.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Roles;
import com.ra.SpringBootJWT.model.repository.RoleRepository;
import com.ra.SpringBootJWT.model.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
