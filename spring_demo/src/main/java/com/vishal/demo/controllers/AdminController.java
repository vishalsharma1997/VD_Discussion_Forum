package com.vishal.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vishal.demo.models.Post;
import com.vishal.demo.models.User;
import com.vishal.demo.services.AdminService;
import com.vishal.demo.services.PostService;
import com.vishal.demo.services.UserService;


@RestController
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PostService postService;

//    @RequestMapping
//    public ModelAndView adminRoot(){
//        ModelAndView modelAndView = new ModelAndView("admin");
//        return modelAndView;
//    }

    @GetMapping
    @ResponseBody
    public Principal viewloginadmin(Principal admin){
        return admin;
    }

    @ResponseBody
    @GetMapping("/viewusers")
    public List<User> viewCustomers(){
       return userService.getAllUsers();
    }
    @ResponseBody
    @GetMapping("/viewposts")
    public List<Post> viewPosts(){
        return postService.getAllPosts();
    }
//    @RequestMapping(value = "/makeadmin", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView makeAdmin(){
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("Make Admin!");
//        modelAndView.setViewName("makeadmin");
//        return modelAndView;
//    }
//    @RequestMapping(value = "/makeadmin", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView makeAdminSave(@RequestParam(value = "emailId",defaultValue = "", required = false) String emailId){
//        ModelAndView modelAndView = new ModelAndView();
//        String fl = "null";
//        System.out.println("EMAILIS:"+emailId.isEmpty());
//        if(!emailId.isEmpty()) {
//            User user = adminService.findByEmailId(emailId);
//            System.out.println("USERFOUND:" + user);
//
//            if (user != null) {
//                adminService.makeAdmin(user);
//                fl = "true";
//                System.out.println("Admin Access Granted !!!");
//            } else {
//                fl = "false";
//                System.out.println("Admin Access Denied !!!");
//            }
//        }
//            modelAndView.addObject("flag", fl);
//
//        modelAndView.setViewName("makeadmin");
//        return modelAndView;
//    }

//    @PutMapping("/makeadmin")
//    @ResponseBody
//    public void makeAdminSave(@RequestBody User user){
//                adminService.makeAdmin(user);
//    }
    
    @PutMapping("/makeadmin/{emailId}")
    @ResponseBody
    public void makeAdminSave(@RequestBody User User , @PathVariable String emailid){
                adminService.makeAdmin(emailid);
    }

    
    @PostMapping("/addpost")
    @ResponseBody
    public Post addpost(@RequestBody Post post){

        return postService.addPost(post);
    }

    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public void deletepost(@PathVariable String id){
        postService.deletePostById(id);
    }


    @DeleteMapping("/deleteuser/{emailid}")
    @ResponseBody
    public void deleteuser(@PathVariable String emailid){
        userService.deleteByUserEmailId(emailid);
    }
    
    @GetMapping("/myposts/{emailId}")
    @ResponseBody
    public List<Post> myposts(@PathVariable String emailId){
        return postService.getAllPostsByUser(emailId);
    }
}
