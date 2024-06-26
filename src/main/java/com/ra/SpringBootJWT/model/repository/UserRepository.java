package com.ra.SpringBootJWT.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ra.SpringBootJWT.model.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);

    Users getUsersByUserName(String userName);
}