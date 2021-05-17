package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Login;
import com.myapp.spring.model.Admin;
import com.myapp.spring.repository.AdminRepository;
import com.myapp.spring.service.LoginService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginIntegrationTest {

	@Autowired
    private AdminRepository repository;
	
	@Autowired
    private LoginService services;
    
    @Autowired
    private MockMvc mockMvc;
    
    private static File DATA_JSON= Paths.get("src","test","resources","Admins.json").toFile();
    
    @BeforeEach
    public void setup() throws JsonParseException, JsonMappingException, IOException {
    
    Admin admins[] = new ObjectMapper().readValue(DATA_JSON,Admin[].class);
    
    //save each product to database
    Arrays.stream(admins).forEach(repository::save);
    }
    @AfterEach
    public void cleanUp() {
    repository.deleteAll();
    }
	
	
	@Test
	@DisplayName("Login Validation")
	public void testLoginValidation() throws Exception {
		
		//Prepare Mock Product
				Login newAdmin =new Login("abc@email.com","12345");
				
				//Login mockUser =new Login("abc","12345");
				
				//mockUser.setLogin();
				
				//doReturn(mockUser).when(repository).save(ArgumentMatchers.any());
				
				// Perform GET Request
				
				mockMvc.perform(MockMvcRequestBuilders.post("/admin/login")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newAdmin)))
				
				//Validate Response body
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.email",is("abc@email.com")))
				.andExpect(jsonPath("$.password",is("12345")));
}
}
