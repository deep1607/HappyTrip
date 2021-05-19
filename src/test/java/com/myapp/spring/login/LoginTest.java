package com.myapp.spring.login;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private AccessRepository repository;
  
  private static File DATA_JSON= Paths.get("src","test","resources","Registration.json").toFile();
  
  @BeforeEach
  public void setup() throws JsonParseException, JsonMappingException, IOException {
	  
	  Access access[]=new ObjectMapper().readValue(DATA_JSON,Access[].class);
		
		//save each product to database
		Arrays.stream(access).forEach(repository::save);
		
    mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .alwaysDo(print())
            .build();
    
  }
  @AfterEach
  public void cleanUp() {
  	repository.deleteAll();
  }
  @Test
  public void loginAvailableForAll() throws Exception {
    mockMvc
            .perform(get("/login"))
            .andExpect(status().isOk());
  }

  @Test
  public void adminCanLog() throws Exception {
	  
	  
	  LoginInfo newuser =new LoginInfo(3,"rajesh@gmail.com","rajesh2021","rajesh","kumar");
		
	  String ADMIN_USERNAME = newuser.getEmail();
	  String ADMIN_PASSWORD = newuser.getPassword();
		
		
		mockMvc.perform(post("/register")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)));
	  
		mockMvc
            .perform(formLogin().user(ADMIN_USERNAME).password(ADMIN_PASSWORD))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername(ADMIN_USERNAME));

   
  }


  @Test
  public void invalidLoginDenied() throws Exception {
    String loginErrorUrl = "/login?error";
    
    LoginInfo newuser =new LoginInfo(3,"rajesh@gmail.com","rajesh2021","rajesh","kumar");
		
    newuser.setId(3);newuser.setEmail("rajesh@gmail.com");newuser.setFirstName("rajesh");
	newuser.setPassword("rajesh2021"); newuser.setLastName("kumar");
    
	String ADMIN_USERNAME = newuser.getEmail();
	  String ADMIN_PASSWORD = newuser.getPassword();
	  
    mockMvc
    	.perform(formLogin().user(ADMIN_USERNAME).password(ADMIN_PASSWORD))
    	.andExpect(status().isFound())
    	.andExpect(redirectedUrl(loginErrorUrl));
    	
 
   
  }
}