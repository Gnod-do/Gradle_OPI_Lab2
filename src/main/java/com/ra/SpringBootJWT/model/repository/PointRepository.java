package com.ra.SpringBootJWT.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ra.SpringBootJWT.model.entity.Points;
import com.ra.SpringBootJWT.model.entity.Users;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Points, Integer> {
    List<Points> getPointsByUser(Users user);

}