package com.vishal.demo.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name = RoleEnum.ROLE_NONE;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER, mappedBy = "roleList")
    List<User> userList;

    private Role(){}

    public Role(RoleEnum role){
        this.name = role;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }
    public void setName(RoleEnum name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
