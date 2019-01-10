package com.vishal.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vishal.demo.models.User;
import com.vishal.demo.services.UserService;

@RestController
public class RootController {

	@Autowired
	private UserService userService;

//	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login(){
//		ModelAndView modelAndView = new ModelAndView();
//		System.out.println("LOGIN!");
//		modelAndView.setViewName("login");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value="/home", method=RequestMethod.GET)
//	public ModelAndView home(){
//		ModelAndView mAndView = new ModelAndView();
//
//		mAndView.setViewName("home");
//		return mAndView;
//	}
	


	@PostMapping("/signup")
	@ResponseBody
	public void signUp(@RequestBody User user) {
		userService.saveUser(user);
	}


	@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
	@RequestMapping("/logout")
	public String logout(){
		return "LOGOUT";
	}
    
}
