package com.myapp.spring.user.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.login.AccessRepository;
import com.myapp.spring.login.LoginInfo;
import com.myapp.spring.login.RegistrationService;

@SpringBootTest
@AutoConfigureMockMvc

public class RegistrationAPITest {

	@MockBean
	private AccessRepository repository;
	

	@MockBean
	private RegistrationService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Test Add New User")
	public void testAddNewUsers() throws Exception {
		
		//Prepare Mock Product
		
		LoginInfo newuser =new LoginInfo(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		LoginInfo mockuser =new LoginInfo(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		//mockuser.setEmail("ravikumar@gmail.com");
		
		// Prepare Mock Service Method
		
		doReturn(mockuser).when(service).registerU(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(post("/register")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(1)))
		.andExpect(jsonPath("$.email",is("ravikumar@gmail.com")))
		.andExpect(jsonPath("$.password",is("ravi2021")))
		.andExpect(jsonPath("$.firstName",is("ravi")))
		.andExpect(jsonPath("$.lastName",is("kumar")));
		
	}
	@Test
	@DisplayName("Test Add New Admin")
	public void testAddNewAdmin() throws Exception {
		
		//Prepare Mock Product
		
		LoginInfo newuser =new LoginInfo(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		LoginInfo mockuser =new LoginInfo(1,"ravikumar@gmail.com","ravi2021","ravi","kumar");
		//mockuser.setEmail("ravikumar@gmail.com");
		
		// Prepare Mock Service Method
		
		doReturn(mockuser).when(service).registerA(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(post("/access")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(1)))
		.andExpect(jsonPath("$.email",is("ravikumar@gmail.com")))
		.andExpect(jsonPath("$.password",is("ravi2021")))
		.andExpect(jsonPath("$.firstName",is("ravi")))
		.andExpect(jsonPath("$.lastName",is("kumar")));
		
	}
	}
