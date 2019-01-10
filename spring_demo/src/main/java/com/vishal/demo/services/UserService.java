package com.vishal.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishal.demo.models.Post;
import com.vishal.demo.models.Role;
import com.vishal.demo.models.RoleEnum;
import com.vishal.demo.models.User;
import com.vishal.demo.repositories.PostRepository;
import com.vishal.demo.repositories.RoleRepository;
import com.vishal.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public List<User> getAllUsers(){
        return repository.findAll();
    }


    public User findByEmailId(String emailId){
        return repository.findByEmailId(emailId);
    }

    public void deleteByUserEmailId(String emailId){
        repository.deleteById(emailId);
    }

    public User saveUser(User user) throws DuplicateKeyException{
        if (repository.existsById(user.getEmailId())){
            throw new DuplicateKeyException("Sorry, the username already exists");
        }

        Optional userRole = roleRepository.getRoleByName(RoleEnum.ROLE_USER);
        Role role;

        String str = user.getEmailId();


        if(str.equals("vishal@vishal.com")) {
            role = new Role(RoleEnum.ROLE_ADMIN);
        }
        else if (userRole.isPresent()){
            role = (Role) userRole.get();
        }else{
            role = new Role(RoleEnum.ROLE_USER);
        }

        user.addRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }


    public User makeAdmin(User user){
        System.out.println("EMAILID : " + repository.existsById(user.getEmailId()) + "USER : " +  user);
        if(!repository.existsById(user.getEmailId())){
            throw new DuplicateKeyException("Sorry, Invalid Username");
        }
        Optional userRole = roleRepository.getRoleByName(RoleEnum.ROLE_USER);
        Role role;
        if (userRole.isPresent()){
            role = (Role) userRole.get();
        }else {
            role = new Role(RoleEnum.ROLE_ADMIN);
        }
        user.addRole(role);
        return repository.save(user);
    }

    public String getLoggedInUserEmailId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }else{
            return principal.toString();
        }
    }
}
