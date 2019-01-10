package com.vishal.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishal.demo.models.User;
import com.vishal.demo.repositories.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    public MyUserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()){
            User myUser = (User) optionalUser.get();

            org.springframework.security.core.userdetails.User details = new org.springframework.security.core.userdetails.User(
                    myUser.getEmailId(),
                    myUser.getPassword(),
                    myUser.getAuthorities()
            );

            return details;
        }else {
            throw new UsernameNotFoundException("Sorry, the requested user was not found.");
        }
    }
}
