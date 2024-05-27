package com.ra.SpringBootJWT.repository;


import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Points;
import com.ra.SpringBootJWT.model.entity.Roles;
import com.ra.SpringBootJWT.model.entity.Users;
import com.ra.SpringBootJWT.model.repository.PointRepository;
import com.ra.SpringBootJWT.model.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    Date date = new Date(2024,12,21,12,12,00);
    Set<Roles> listRoles = new HashSet<>();

    @Test
    public void PointRepository_SaveAll_ReturnPoints() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user = new Users();
        user.setUserName("gnodd");
        user.setPassword("21052002");
        user.setCreated(date);

        Users savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();

        Points point = new Points();
        point.setUser(savedUser);
        point.setY(20.0);
        point.setX(10.0);
        point.setR(5.0);
        point.setResult("True");

        Points savedPoint = pointRepository.save(point);

        Assertions.assertThat(savedPoint).isNotNull();

    }
}
