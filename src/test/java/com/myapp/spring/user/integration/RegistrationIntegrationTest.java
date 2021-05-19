package com.myapp.spring.user.integration;



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
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.login.Access;
import com.myapp.spring.login.AccessRepository;
import com.myapp.spring.login.LoginInfo;
import com.myapp.spring.login.RegistrationService;

@SpringBootTest
@AutoConfigureMockMvc

public class RegistrationIntegrationTest {

	@Autowired
	private AccessRepository repository;
	
	@Autowired
	private RegistrationService Service;
	
	@Autowired
	private MockMvc mockMvc;
	
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
	@DisplayName("Test Add New User")
	public void testAddNewUsers() throws Exception {
		
		//Prepare Mock Product
	
		LoginInfo newuser =new LoginInfo(3,"rajesh@gmail.com","rajesh2021","rajesh","kumar");
		
		
		// Perform GET Request
		
		mockMvc.perform(post("/register")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(3)))
		.andExpect(jsonPath("$.email",is("rajesh@gmail.com")))
		.andExpect(jsonPath("$.password",is("rajesh2021")))
		.andExpect(jsonPath("$.firstName",is("rajesh")))
		.andExpect(jsonPath("$.lastName",is("kumar")));
		
	}
	
	@Test
	@DisplayName("Test Add New Admin")
	public void testAddNewAdmin() throws Exception {
		
		//Prepare Mock Product
	
		LoginInfo newuser =new LoginInfo(3,"rajesh@gmail.com","rajesh2021","rajesh","kumar");
		
		
		// Perform GET Request
		
		mockMvc.perform(post("/access")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(3)))
		.andExpect(jsonPath("$.email",is("rajesh@gmail.com")))
		.andExpect(jsonPath("$.password",is("rajesh2021")))
		.andExpect(jsonPath("$.firstName",is("rajesh")))
		.andExpect(jsonPath("$.lastName",is("kumar")));
		
	}
	}

