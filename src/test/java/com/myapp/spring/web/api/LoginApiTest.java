package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Login;
import com.myapp.spring.model.Admin;
import com.myapp.spring.repository.AdminRepository;
import com.myapp.spring.service.LoginService;

@SpringBootTest
@AutoConfigureMockMvc

public class LoginApiTest {

	@MockBean
	private AdminRepository repository;
	
	@MockBean
	private LoginService services;
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	//@DisplayName("Test Add New User")
	//public void testAddNewUser() throws Exception {
		
		//Prepare Mock Product
		//Login newUser =new Login("abc","12345");
		
		//Login mockUser =new Login("abc","12345");
		
		//mockUser.setLogin();
		
		//doReturn(mockUser).when(repository).save(ArgumentMatchers.any());
		
		//mockMvc.perform(post("/check/login/login")
		// Validate Status should be 200 ok and json response recived
		//.contentType(MediaType.APPLICATION_JSON_VALUE)
		//.content(new ObjectMapper().writeValueAsString(newUser)))
		
		//Validate Response body
		
		//.andExpect(status().isCreated())
		//.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//.andExpect(jsonPath("$.UserName",is("abc")))
		//.andExpect(jsonPath("$.password",is("12345")));
	
		
	//}
	

	@Test
	@DisplayName("Login Validation")
	public void testLoginValidation() throws Exception {
		
		//Prepare Mock Product
				Login newUser =new Login("abc@email.com","12345");
				
				Login mockUser =new Login("abc@email.com","12345");
				
				
				Admin admin = new Admin( 1, "abc@email.com","12345");
				
				//mockUser.setLogin();
				
				// Prepare Mock Service Method
				
				
				doReturn(mockUser).when(services).logincheck(ArgumentMatchers.any());
				
				// Perform GET Request
				
				mockMvc.perform(post("/admin/login")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newUser)))
				
				//Validate Response body
				
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.email",is("abc@email.com")))
				.andExpect(jsonPath("$.password",is("12345")));
	}
	


		
	
}
