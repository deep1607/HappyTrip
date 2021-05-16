package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AirlineIntegrationTest {
	
	@Autowired
	private AirlineRepository repository;
	
	@Autowired
	private MockMvc mockMvc;

	private static File DATA_JSON= Paths.get("src","test","resources","Airlines.json").toFile();

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException{

		Airlines airlines []=new ObjectMapper().readValue(DATA_JSON,Airlines[].class);

		Arrays.stream(airlines).forEach(repository::save);
	
	}

	@AfterEach
	public void cleanUp() {
	
		repository.deleteAll();
	
	}

	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAll
	@Test
	@DisplayName("Test Get All Airlines- GET /admin/airlines/findAll")
	public void testfindAllAirlines() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAll"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(jsonPath("$[0].Airline_code", is("K12")))
		.andExpect(jsonPath("$[0].Airline_name", is("Kingfisher")))
		
		.andExpect(jsonPath("$[1].Airline_code", is("EA12")))
		.andExpect(jsonPath("$[1].Airline_name", is("EithaadArirways")))

		.andExpect(jsonPath("$[2].Airline_code", is("AI12")))
		.andExpect(jsonPath("$[2].Airline_name", is("AirIndia")))

		.andExpect(jsonPath("$[3].Airline_code", is("BA12")))
		.andExpect(jsonPath("$[3].Airline_name", is("BritishAirlines")));
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_name}
	@Test
	@DisplayName("Test Find Product By Airline_name - GET /admin/airlines/find/{Airline_name}")
	public void testfindAirlinesByName() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/find/{Airline_name}","Kingfisher"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Airline_code", is("K12")))
		.andExpect(jsonPath("$.Airline_name", is("Kingfisher")));
		
//		.andExpect(jsonPath("$[1].Airline_code", is("EA12")))
//		.andExpect(jsonPath("$[1].Airline_name", is("EithaadArirways")))
//
//		.andExpect(jsonPath("$[2].Airline_code", is("AI12")))
//		.andExpect(jsonPath("$[2].Airline_name", is("AirIndia")))
//
//		.andExpect(jsonPath("$[3].Airline_code", is("BA12")))
//		.andExpect(jsonPath("$[3].Airline_name", is("BritishAirlines")));
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_code}
	@Test
	@DisplayName("Test Find Product By Airline_code - GET /admin/airlines/find/{Airline_code}")
	public void testfindAirlinesByCode() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/find/{Airline_name}","K12"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Airline_code", is("K12")))
		.andExpect(jsonPath("$.Airline_name", is("Kingfisher")));
		
//		.andExpect(jsonPath("$[1].Airline_code", is("EA12")))
//		.andExpect(jsonPath("$[1].Airline_name", is("EithaadArirways")))
//
//		.andExpect(jsonPath("$[2].Airline_code", is("AI12")))
//		.andExpect(jsonPath("$[2].Airline_name", is("AirIndia")))
//
//		.andExpect(jsonPath("$[3].Airline_code", is("BA12")))
//		.andExpect(jsonPath("$[3].Airline_name", is("BritishAirlines")));
		
	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/airlines/add")
	public void testaddNewAirline() throws Exception{

		Airlines newAirlines = new Airlines("AA12","AmericanAirlines");
		newAirlines.setAirline_code("AA12");
		newAirlines.setAirline_name("AmericanAirlines");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/airlines/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newAirlines)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Airline_code", is("AA12")))
		.andExpect(jsonPath("$.Airline_name", is("AmericanAirlines")));

	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add/bulk
	@Test
	@DisplayName("Test Bulk Insert New Airline - POST /admin/airlines/add/bulk")
	public void testbulkAirlineInsert() throws Exception{

		Airlines newAirlines1 = new Airlines("AA12","AmericanAirlines");
		newAirlines1.setAirline_code("AA12");
		newAirlines1.setAirline_name("AmericanAirlines");
		
		Airlines newAirlines2 = new Airlines("QA12","QattarAirways");
		newAirlines2.setAirline_code("QA12");
		newAirlines2.setAirline_name("QattarAirways");
		
		List<Airlines> newAirlines = new ArrayList<>();
		newAirlines.add(newAirlines1);
		newAirlines.add(newAirlines1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/airlines/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newAirlines)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$[0].Airline_code", is("AA12")))
		.andExpect(jsonPath("$[0].Airline_name", is("AmericanAirlines")))
		
		.andExpect(jsonPath("$[1].Airline_code", is("QA12")))
		.andExpect(jsonPath("$[1].Airline_name", is("QattarAirways")));

	}

	//Admin should be able to update an airline
	//http://localhost:8888/api/v1/products	

}
