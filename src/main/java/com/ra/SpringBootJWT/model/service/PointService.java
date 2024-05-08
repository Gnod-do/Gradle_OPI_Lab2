package com.ra.SpringBootJWT.model.service;

import com.ra.SpringBootJWT.model.entity.Points;
import com.ra.SpringBootJWT.model.entity.Users;

import java.util.List;

public interface PointService {
    List<Points> getAllPointsByUser(Users user);
}
