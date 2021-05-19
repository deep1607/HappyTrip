package com.myapp.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationAPI {
	
	@Autowired
	private AccessRepository repository;
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/access")
	public ResponseEntity<LoginInfo> adminRegistration(@RequestBody LoginInfo loginInfo){
		
		return new ResponseEntity<LoginInfo>(service.registerA(loginInfo),HttpStatus.CREATED);
	}
	@PostMapping("/register")
    
	public ResponseEntity<LoginInfo> userRegistration(@RequestBody LoginInfo loginInfo){
		
		return new ResponseEntity<LoginInfo>(service.registerU(loginInfo),HttpStatus.CREATED);
		}
}
