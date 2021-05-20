package com.myapp.spring.user.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.user.model.Profile;
import com.myapp.spring.user.repository.ProfileRepository;


@RestController
@RequestMapping("/user")
public class ProfileAPI{


//Dependency Injection
	@Autowired
	private ProfileRepository repository;
	
	
	
	
	@GetMapping("profile/view/{email}")
	
	   public ResponseEntity<Profile> viewProfile(
		  @PathVariable String email){
			
		Profile profile = null;
		if(repository.findByEmail(email).isPresent()) {
			profile=repository.findByEmail(email).orElseGet(Profile::new);
		}
			return new ResponseEntity<>(profile,HttpStatus.OK);
			
			}

	
@PostMapping("profile/add")
    
	public ResponseEntity<Profile> addNewProfile(@RequestBody Profile profile){
		
		return new ResponseEntity<>(repository.save(profile),HttpStatus.CREATED);
		}
   
}
