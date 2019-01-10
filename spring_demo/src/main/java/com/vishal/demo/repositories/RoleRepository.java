package com.vishal.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vishal.demo.models.Role;
import com.vishal.demo.models.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> getRoleByName(RoleEnum name);
}
