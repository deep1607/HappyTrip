package com.myapp.spring.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.login.Access;
import com.myapp.spring.login.AccessRepository;
import com.myapp.spring.login.LoginInfo;

 

@SpringBootTest
public class AccessRepositoryTest {
 
	@Autowired
	private AccessRepository repository;
	
	
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
	
	Access user =new Access(1,"deep@mail.com","pass","USER",true,"Deep","Shah");
	
	Access savedUser =repository.save(user);
	Assertions.assertNotNull(savedUser," New user should be saved");
	
	Assertions.assertNotNull(savedUser.getId()," New Product should have id");
	
	Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
	
}
     

         
    
}
