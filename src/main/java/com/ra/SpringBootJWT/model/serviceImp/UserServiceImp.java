package com.ra.SpringBootJWT.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.SpringBootJWT.model.entity.Users;
import com.ra.SpringBootJWT.model.repository.UserRepository;
import com.ra.SpringBootJWT.model.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public Users saveOrUpdate(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUsersByUserName(String userName) {
        return userRepository.getUsersByUserName(userName);
    }
}

