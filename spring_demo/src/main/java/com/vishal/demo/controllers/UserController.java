package com.vishal.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.demo.models.Post;
import com.vishal.demo.repositories.RoleRepository;
import com.vishal.demo.services.PostService;
import com.vishal.demo.services.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    @ResponseBody
    public Principal getUser(Principal user){
        return user;
    }

//    @RequestMapping
//    public ModelAndView userRoot(){
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", userService.getLoggedInUserEmailId());
//        modelAndView.addObject("posts", postService.getAllPosts());
//        System.out.println("ALL POSTS: ");
//        System.out.println(postService.getAllPostsByUser(userService.getLoggedInUserEmailId()));
//        modelAndView.setViewName("user");
//        return modelAndView;
//    }

    @GetMapping("/viewposts")
    @ResponseBody
    public List<Post> userRoot(){
        return postService.getAllPostsByUser(userService.getLoggedInUserEmailId());
    }

//    @RequestMapping("/addpost")
//    public ModelAndView addPost(){
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("post", new Post());
//        modelAndView.setViewName("addpost");
//
//        return modelAndView;
//    }

    @PostMapping("/savepost")
    @ResponseBody
    public Post savepost(@RequestBody Post post){

        return postService.addPost(post);
    }

//    @RequestMapping(value="/save", method= RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute User user){
//
//        userService.saveUser(user);
//
//        System.out.println("USER ADDED!");
//        return userRoot();
//    }
    
    @GetMapping("/myposts/{emailId}")
    @ResponseBody
    public List<Post> myposts(@PathVariable String emailId){
        return postService.getAllPostsByUser(emailId);
    }
}