package com.myapp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.myapp.spring.repository.AdminRepository;
import com.myapp.spring.model.Login;
import com.myapp.spring.model.Admin;


@Service
public class LoginService {
	
	@Autowired
	private AdminRepository repository;
	
	//@Autowired
	//private LoginRepository repo;
	
	public Login logincheck(Login loginInfo) {
		
		String email;
		String password;
		email=loginInfo.getEmail();
		password=loginInfo.getPassword();
		
		try {
		Admin admin = repository.findByemailAndPassword(email, password).get();
		}catch(Exception e){
			
			loginInfo.setEmail("");
			loginInfo.setPassword("");
		}
		
		
		
//		if(user != null) {
//			
//			return loginInfo;
//		}
//		else {
//			return emptylogin;
//		}
		return loginInfo;
	}

}
