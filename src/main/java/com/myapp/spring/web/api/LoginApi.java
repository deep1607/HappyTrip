package com.myapp.spring.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Login;
	import com.myapp.spring.repository.LoginRepository;

	//This is a class which exposes rest api's
	@RestController
	@RequestMapping("/check/login")
	public class LoginApi {
		
		//Dependency Injection
		@Autowired
		private LoginRepository repository;
		
		@PostMapping("/login")
		public ResponseEntity<Login> saveNewUser(@RequestBody Login user){
		
		return new ResponseEntity<Login>(repository.save(user),HttpStatus.CREATED);
		}
		
		//@GetMapping("/{id}")
	//	public ResponseEntity<Product> findById(@PathVariable("id") Integer id ){
		
		//return new ResponseEntity<Product>(repository.findById(id).get(),HttpStatus.OK);
		//}
		
		
		
	     
	}
