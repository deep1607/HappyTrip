package com.myapp.spring.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.myapp.spring.model.Login;

import com.myapp.spring.model.Admin;
import com.myapp.spring.repository.AdminRepository;
import com.myapp.spring.service.LoginService;

	//This is a class which exposes rest api's
	@RestController
	@RequestMapping("/admin/login")
	public class LoginApi {
		
		//Dependency Injection
		@Autowired
		private AdminRepository repository;
		
		@Autowired
		private LoginService service;
		
		//@GetMapping("/{id}")
	//	public ResponseEntity<Product> findById(@PathVariable("id") Integer id ){
		
		//return new ResponseEntity<Product>(repository.findById(id).get(),HttpStatus.OK);
		//}
		@PostMapping
		public ResponseEntity<Login> login(@RequestBody Login loginInfo ){
			
			return new ResponseEntity<Login>(service.logincheck(loginInfo),HttpStatus.OK);
		}
		
		@GetMapping
		public ResponseEntity<List<Admin>> findAll(){
			
			return new ResponseEntity<List<Admin>>(repository.findAll(),HttpStatus.OK);
			}
		
	     
	}
