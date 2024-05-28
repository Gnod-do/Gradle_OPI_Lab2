package com.ra.SpringBootJWT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import com.ra.SpringBootJWT.model.entity.Roles;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
//@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;


    @NotEmpty
    @Column(name = "UserName", unique = true, nullable = false)
    private String userName;

    @NotEmpty
    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Created")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId"))
    private Set<Roles> listRoles = new HashSet<>();

}

