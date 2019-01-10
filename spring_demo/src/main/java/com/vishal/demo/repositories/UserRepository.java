package com.vishal.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vishal.demo.models.Role;
import com.vishal.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public List<Role> getRoleListByEmailId(String emailId);
    
    public User findByEmailId(String emailId);
}
