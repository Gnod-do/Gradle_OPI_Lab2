package com.ra.SpringBootJWT.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Roles;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(ERole roleName);
}
