package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AirlineAPITest {
	
	@MockBean
	private AirlineRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAll
	@Test
	@DisplayName("Test Find All Airlines- GET /admin/airlines/findAll")
	public void testfindAllAirlines() throws Exception{

		Airlines airlines1 = new Airlines("AA12","AmericanAirlines");
		airlines1.setAirline_code("AA12");
		airlines1.setAirline_name("AmericanAirlines");
		
		Airlines airlines2 = new Airlines("QA12","QattarAirways");
		airlines2.setAirline_code("QA12");
		airlines2.setAirline_name("QattarAirways");
		
		List<Airlines> airlines = new ArrayList<>();
		airlines.add(airlines1);
		airlines.add(airlines1);
		
		doReturn(airlines).when(repository).findAll();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/findAll"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(jsonPath("$[0].Airline_code", is("AA12")))
		.andExpect(jsonPath("$[0].Airline_name", is("AmericanAirlines")))
		
		.andExpect(jsonPath("$[1].Airline_code", is("QA12")))
		.andExpect(jsonPath("$[1].Airline_name", is("QattarAirways")));
		
	}	

	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_name}
	@Test
	@DisplayName("Test Find Product By Airline_name - GET /admin/airlines/find/{Airline_name}")
	public void testfindAirlinesByName() throws Exception{

		Airlines airlines = new Airlines("AA12","AmericanAirlines");
		airlines.setAirline_code("AA12");
		airlines.setAirline_name("AmericanAirlines");
		
		doReturn(Optional.of(airlines)).when(repository).findByAirline_name(airlines.getAirline_name());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/find/{Airline_name}",1))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Airline_code", is("AA12")))
		.andExpect(jsonPath("$.Airline_name", is("AmericanAirlines")));
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_code}
	@Test
	@DisplayName("Test Find Product By Airline_code - GET /admin/airlines/find/{Airline_code}")
	public void testfindAirlinesByCode() throws Exception{

		Airlines airlines = new Airlines("AA12","AmericanAirlines");
		airlines.setAirline_code("AA12");
		airlines.setAirline_name("AmericanAirlines");
		
		doReturn(Optional.of(airlines)).when(repository).findByAirline_code(airlines.getAirline_code());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/airlines/find/{Airline_code}",1))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Airline_code", is("AA12")))
		.andExpect(jsonPath("$.Airline_name", is("AmericanAirlines")));
		
	}
		
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/airlines/add")
	public void testaddNewAirline() throws Exception{

		Airlines newAirlines = new Airlines("AA12","AmericanAirlines");
		newAirlines.setAirline_code("AA12");
		newAirlines.setAirline_name("AmericanAirlines");
		
		Airlines mockAirlines = new Airlines("AA12","AmericanAirlines");
		mockAirlines.setAirline_code("AA12");
		mockAirlines.setAirline_name("AmericanAirlines");
		
		doReturn(mockAirlines).when(repository).save(ArgumentMatchers.any());
		
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
		newAirlines.add(newAirlines2);
		
		Airlines mockAirlines1 = new Airlines("AA12","AmericanAirlines");
		mockAirlines1.setAirline_code("AA12");
		mockAirlines1.setAirline_name("AmericanAirlines");
		
		Airlines mockAirlines2 = new Airlines("QA12","QattarAirways");
		mockAirlines2.setAirline_code("QA12");
		mockAirlines2.setAirline_name("QattarAirways");
		
		List<Airlines> mockAirlines = new ArrayList<>();
		mockAirlines.add(mockAirlines1);
		mockAirlines.add(mockAirlines2);
		
		doReturn(mockAirlines).when(repository).save(ArgumentMatchers.any());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/airlines/add/bulk")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newAirlines)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$[0].Airline_code", is("AA12")))
		.andExpect(jsonPath("$[0].Airline_name", is("AmericanAirlines")))
		
		.andExpect(jsonPath("$[1].Airline_code", is("QA12")))
		.andExpect(jsonPath("$[1].Airline_name", is("QattarAirways")));
	}
	

}
