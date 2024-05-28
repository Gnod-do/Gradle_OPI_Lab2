package com.ra.SpringBootJWT.controller;

import com.ra.SpringBootJWT.jwt.JwtTokenProvider;
import com.ra.SpringBootJWT.model.entity.ERole;
import com.ra.SpringBootJWT.model.entity.Roles;
import com.ra.SpringBootJWT.model.entity.Users;
import com.ra.SpringBootJWT.model.serviceImp.RoleServiceImp;
import com.ra.SpringBootJWT.model.serviceImp.UserServiceImp;
import com.ra.SpringBootJWT.payload.request.LoginRequest;
import com.ra.SpringBootJWT.payload.request.SignupRequest;
import com.ra.SpringBootJWT.payload.response.JwtResponse;
import com.ra.SpringBootJWT.security.CustomUserDetails;
import com.ra.SpringBootJWT.security.CustomUserDetailsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImp userServiceImp;

    @MockBean
    private RoleServiceImp roleServiceImp;

    @MockBean
    private PasswordEncoder encoder;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    public void Test_RegisterUser_Success() throws Exception {
        Set<Roles> roles = new HashSet<>();
        Roles userRole = new Roles();
        userRole.setRoleName(ERole.ROLE_USER);
        roles.add(userRole);
        Set<String> rolesStr = roles.stream()
                                    .map(role -> role.getRoleName().toString())
                                    .collect(Collectors.toSet());
        SignupRequest signupRequest = new SignupRequest("username","password",rolesStr);

        Users savedUser = new Users();
        savedUser.setUserName(signupRequest.getUserName());
        savedUser.setPassword(encoder.encode(signupRequest.getPassword()));
        savedUser.setListRoles(roles);
        savedUser.setCreated(new Date());

        when(userServiceImp.existsByUserName(signupRequest.getUserName())).thenReturn(false);
        when(roleServiceImp.findByRoleName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));
        when(userServiceImp.saveOrUpdate(savedUser)).thenReturn(savedUser);

        ResponseEntity<?> response = userController.registerUser(signupRequest);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


    @Test
    public void Test_RegisterUser_UserNameAlreadyExists() {
        Set<Roles> roles = new HashSet<>();
        Roles userRole = new Roles();
        userRole.setRoleName(ERole.ROLE_USER);
        roles.add(userRole);
        Set<String> rolesStr = roles.stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.toSet());
        SignupRequest signupRequest = new SignupRequest("username","password",rolesStr);

        when(userServiceImp.existsByUserName(signupRequest.getUserName())).thenReturn(true);

        ResponseEntity<?> response = userController.registerUser(signupRequest);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void Test_RegisterUser_EmptyUsernameAndPassword() {
        SignupRequest signupRequest = new SignupRequest("","",new HashSet<>());

        ResponseEntity<?> response = userController.registerUser(signupRequest);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

//    @Test
//    public void testLoginUser_Success() {
//        // Arrange
//        String jwtToken = "jwt_token";
//        String username = "username";
//        String password = "password";
//
//        Set<Roles> roles = new HashSet<>();
//        Roles userRole = new Roles();
//        userRole.setRoleName(ERole.ROLE_USER);
//        roles.add(userRole);
//
//        Users users = new Users(username, password, roles, new Date());
//        CustomUserDetails userDetails = CustomUserDetails.mapUserToUserDetail(users);
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenAnswer(invocation -> {
//                    UsernamePasswordAuthenticationToken token = invocation.getArgument(0);
//                    if (token.getName().equals(username) && token.getCredentials().equals(password)) {
//                        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//                    }
//                    throw new BadCredentialsException("Bad credentials");
//                });
//        when(tokenProvider.generateToken(any(CustomUserDetails.class))).thenReturn(jwtToken);
//
//        // Act
//        LoginRequest loginRequest = new LoginRequest(username, password);
//        ResponseEntity<?> response = userController.loginUser(loginRequest);
//
//        System.out.println(response);
//
//        // Assert
////        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}
