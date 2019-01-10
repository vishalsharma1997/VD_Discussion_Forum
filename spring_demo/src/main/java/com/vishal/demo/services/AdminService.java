package com.vishal.demo.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.vishal.demo.models.Role;
import com.vishal.demo.models.RoleEnum;
import com.vishal.demo.models.User;
import com.vishal.demo.repositories.RoleRepository;
import com.vishal.demo.repositories.UserRepository;


@Service
public class AdminService {

    @Autowired
    private UserRepository repository;

    @Autowired
    protected RoleRepository roleRepository;



    public User findByEmailId(String emailId){
        return repository.findByEmailId(emailId);
    }


//    public User makeAdmin(User user){
//        System.out.println("EMAILID : " + repository.existsById(user.getEmailId()) + "USER : " +  user);
//        if(!repository.existsById(user.getEmailId())){
//            throw new DuplicateKeyException("Sorry, Invalid Username");
//        }
//
//        if(user.hasRole(new Role(RoleEnum.ROLE_ADMIN))){
//            return user;
//        }
//
//        Optional userRole = roleRepository.getRoleByName(RoleEnum.ROLE_ADMIN);
//        Role role;
//        if (userRole.isPresent()){
//            role = (Role) userRole.get();
//        }else {
//            role = new Role(RoleEnum.ROLE_ADMIN);
//        }
//        user.addRole(role);
//        return repository.save(user);
//    }

    public User makeAdmin(String emailId){
        System.out.println("EMAILID : " + repository.existsById(emailId));
        if(!repository.existsById(emailId)){
            throw new DuplicateKeyException("Sorry, Invalid Username");
        }

        User user = repository.findByEmailId(emailId);
        
        if(user.hasRole(new Role(RoleEnum.ROLE_ADMIN))){
            return user;
        }

        Optional userRole = roleRepository.getRoleByName(RoleEnum.ROLE_ADMIN);
        Role role;
        if (userRole.isPresent()){
            role = (Role) userRole.get();
        }else {
            role = new Role(RoleEnum.ROLE_ADMIN);
        }
        user.addRole(role);
        return repository.save(user);
    }
}
