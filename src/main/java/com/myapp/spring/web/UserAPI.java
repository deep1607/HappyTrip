package com.myapp.spring.web;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;
	@Controller
	public class UserAPI {
	 
	    @Autowired
	    private UserRepository userRepo;
	     
	    @GetMapping("")
	    public String viewHomePage() {
	        return "index";
	    }
	    
	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	         
	        return "signup_form";
	    }
	}



