package com.ra.SpringBootJWT.repository;


import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Roles;
import com.ra.SpringBootJWT.model.entity.Users;
import com.ra.SpringBootJWT.model.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    Date date = new Date(2024,12,21,12,12,00);
    Set<Roles> listRoles = new HashSet<>();

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user = new Users();
        user.setUserName("gnodd");
        user.setPassword("21052002");
        user.setCreated(date);


        Users savedUser = userRepository.save(user);
//        Assertions.assertNotNull(savedUser);
        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThenOneUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);


        Users user2 = new Users();
        user2.setUserName("gnod21");
        user2.setPassword("21052002");
        user2.setCreated(date);

        userRepository.save(user1);
        userRepository.save(user2);

        List<Users> userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
		Assertions.assertThat(userList.size()).isEqualTo(2);

    }


    @Test
    public void UserRepository_FindById_ReturUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);

        userRepository.save(user1);

        Users userGet = userRepository.findById(user1.getUserId()).get();

        Assertions.assertThat(userGet).isNotNull();


    }

    @Test
    public void UserRepository_FindByUserName_ReturUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);

        userRepository.save(user1);

        Users userGet = userRepository.findByUserName(user1.getUserName());

        Assertions.assertThat(userGet).isNotNull();


    }

    @Test
    public void UserRepository_UpdateUser_ReturUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);

        userRepository.save(user1);

        Users userGet = userRepository.findById(user1.getUserId()).get();
        userGet.setUserName("NewName");
        userGet.setPassword("1111111");

        Users updatedUser = userRepository.save(userGet);

        Assertions.assertThat(updatedUser.getUserName()).isNotNull();
        Assertions.assertThat(updatedUser.getPassword()).isNotNull();

    }

    @Test
    public void UserRepository_DeleteUser_ReturUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);

        userRepository.save(user1);

        userRepository.deleteById(user1.getUserId());

        Optional<Users> userGet = userRepository.findById(user1.getUserId());

        Assertions.assertThat(userGet).isEmpty();

    }

    @Test
    public void UserRepository_UserExit_ReturUser() {
        Roles role = new Roles();
        role.setRoleName(ERole.ROLE_USER);
        listRoles.add(role);
        Users user1 = new Users();
        user1.setUserName("gnod11");
        user1.setPassword("21052002");
        user1.setCreated(date);

        userRepository.save(user1);

        Boolean exists = userRepository.existsByUserName(user1.getUserName());

        Assertions.assertThat(exists).isTrue();

    }





}
