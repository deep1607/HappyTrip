package com.myapp.spring.login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 

@SpringBootTest
public class AccessRepositoryTest {
 
	@Autowired
	private AccessRepository repository;
	
//	@Autowired
//	private AccessDetailsService service;
	
	
private static File DATA_JSON= Paths.get("src","test","resources","Registration.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Access access[]=new ObjectMapper().readValue(DATA_JSON,Access[].class);
	
	//save each product to database
	Arrays.stream(access).forEach(repository::save);
	}

@AfterEach
public void cleanUp() {
	repository.deleteAll();
}

@Test

@DisplayName("Test user saved sucessfully")
public void testProductSavedSucessfully() {
	
	Access user =new Access(2,"deep@mail.com","pass","USER",true,"Deep","Shah");
	
	Access savedUser =repository.save(user);
	Assertions.assertNotNull(savedUser," New user should be saved");
	
	Assertions.assertNotNull(savedUser.getId()," New Product should have id");
	
	Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
	Assertions.assertEquals(user.getLastName(), savedUser.getLastName());
	
}

@Test
@DisplayName("Testing repository funtions")
public void testfunction() {
	
	Access admin =new Access(1,"ravikumar@gmail.com","ravi2021","ADMIN",true,"ravi","kumar");
	
	String email =admin.getEmail();
	
	Access admin2= repository.findByEmail(email).get();
	
	Assertions.assertNotNull(admin2," Admin found");
	
	Assertions.assertNotNull(admin2.getId()," Admin should have id");
	
	Assertions.assertEquals(admin.getFirstName(), admin2.getFirstName());
	
}
 }
