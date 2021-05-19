package com.myapp.spring.admin.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.admin.model.RouteModel;
import com.myapp.spring.admin.repository.RouteRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class RouteIntegrationTest {

	@Autowired
	private RouteRepository repository;
	
//	@Autowired
//	private RouteService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static File DATA_JSON = Paths.get("src","test","resources","route.json").toFile();
	
	@BeforeEach
	public void setUp() throws JsonEOFException,JsonMappingException,IOException{
		RouteModel cities[]= new ObjectMapper().readValue(DATA_JSON, RouteModel[].class);
		Arrays.stream(cities).forEach(repository::save);
	}

	@AfterEach
	public void cleanUp() {
		repository.deleteAll();
		
	}
	
	@Test
	@DisplayName("Test Add route")
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testAddNewRoute() throws Exception{
		
		RouteModel newroute=new RouteModel(4, "Mumbai", "Bangalore", 836);
		
		String json = new ObjectMapper().writeValueAsString(newroute);
		
		
		mockMvc.perform(post("/admin/route")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newroute)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(json));
	}
	
	@Test
	@DisplayName("Test view all city information")
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testGetAllRoute() throws Exception {
		
		
		RouteModel route1=new RouteModel(1, "Mumbai", "Delhi", 1163);
		RouteModel route2=new RouteModel(2, "Delhi", "Mumbai", 1163);
		RouteModel route3=new RouteModel(3, "Banglore", "Mumbai", 836);
		
		List<RouteModel> routes = new ArrayList<>();
		routes.add(route1);
		routes.add(route2);
		routes.add(route3);
		
		String json = new ObjectMapper().writeValueAsString(routes);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/route"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(content().json(json));
	}
	
//	
	
	@Test
	@DisplayName("Test Update Route")
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testUpdateRoute() throws Exception{
		
		RouteModel newroute=new RouteModel(1,"Mumbai", "Delhi", 1163);
		
		 String json = new ObjectMapper().writeValueAsString(newroute);
		
		mockMvc.perform(put("/admin/route/update")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json).accept(MediaType.APPLICATION_JSON))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().json(json));
				
	}
	@Test
	@DisplayName("Test Add list of route")
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testAddAllRoute() throws Exception{
		
		//Prepare Mock Product
				RouteModel route1=new RouteModel(4, "Mumbai", "Bangalore", 836);
				RouteModel route2=new RouteModel(5, "Delhi", "Bangalore", 1737);
			
				List<RouteModel> routes = new ArrayList<>();
				routes.add(route1);
				routes.add(route2);
						
		String json = new ObjectMapper().writeValueAsString(routes);
		
		mockMvc.perform(post("/admin/route/bulk")
				// Validate Status should be 200 ok and json response recived
				
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(routes)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(json));

	}
	
}
