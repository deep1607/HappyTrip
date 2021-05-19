package com.myapp.spring.user.integration;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.user.model.Profile;
import com.myapp.spring.user.repository.ProfileRepository;


@SpringBootTest
@AutoConfigureMockMvc

public class ProfileIntegrationTest {

	@Autowired
	private ProfileRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
private static File DATA_JSON= Paths.get("src","test","resources","profile.json").toFile();
	
	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
	
	Profile profiles[]=new ObjectMapper().readValue(DATA_JSON,Profile[].class);
	
	//save each product to database
	Arrays.stream(profiles).forEach(repository::save);
	}

@AfterEach
public void cleanUp() {
	repository.deleteAll();
	
}
	@Test
	@DisplayName("Test View by Email - GET user/profile/view/{email}")
	@WithMockUser(username="user",roles={"USER"})
	public void testGetProfileByEmail() throws Exception {
		
		//Prepare Mock Product
		Profile profile =new Profile(2,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
		
		//profile.setEmail();
		String Email=profile.getEmail();
		// Prepare Mock Service Method
		
		//doReturn(Optional.of(profile)).when(repository).findByEmail(Email);
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/view/{email}",Email))
		// Validate Status should be 200 ok and json response recived
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		//Validate Response body
		
		.andExpect(jsonPath("$.id",is(2)))
		.andExpect(jsonPath("$.fullName",is("Aashi V")))
		.andExpect(jsonPath("$.gender",is("Female")))
		.andExpect(jsonPath("$.dob",is("9Nov")))
		.andExpect(jsonPath("$.email",is("aashi598@gmail.com")))
		.andExpect(jsonPath("$.address",is("NFL guna")))
		.andExpect(jsonPath("$.state",is("madhya pradesh")))
		.andExpect(jsonPath("$.country",is("INDIA")))
		.andExpect(jsonPath("$.mobileNumber",is(123333)))
		.andExpect(jsonPath("$.pincode",is(473111)));
	}
	
	
	@Test
	@DisplayName("Test Add New Profile")
	@WithMockUser(username="user",roles={"USER"})
	public void testAddNewProfiles() throws Exception {
		
		//Prepare Mock Product
		Profile newprofile =new Profile(6,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
		
		//Profile mockprofile =new Profile(6,"Aashi V","Female","9Nov","aashi598@gmail.com","NFL guna","madhya pradesh","GUNA","INDIA",123333,473111);
		
		//mockprofile.setProfileid(1);
		
		// Prepare Mock Service Method
		
		//doReturn(mockprofile).when(repository).save(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(post("/user/profile/add")
		// Validate Status should be 200 ok and json response received
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newprofile)))
		
		//Validate Response body
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id",is(6)))
		.andExpect(jsonPath("$.fullName",is("Aashi V")))
		.andExpect(jsonPath("$.gender",is("Female")))
		.andExpect(jsonPath("$.dob",is("9Nov")))
		.andExpect(jsonPath("$.email",is("aashi598@gmail.com")))
		.andExpect(jsonPath("$.address",is("NFL guna")))
		.andExpect(jsonPath("$.state",is("madhya pradesh")))
		.andExpect(jsonPath("$.country",is("INDIA")))
		.andExpect(jsonPath("$.mobileNumber",is(123333)))
		.andExpect(jsonPath("$.pincode",is(473111)));
		     
	}


	
	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
