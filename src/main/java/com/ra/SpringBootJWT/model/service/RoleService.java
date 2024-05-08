package com.ra.SpringBootJWT.model.service;

import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}
