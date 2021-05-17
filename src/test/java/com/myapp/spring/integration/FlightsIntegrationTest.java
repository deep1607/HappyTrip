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
import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightsIntegrationTest {
	
	@Autowired
	private FlightsRepository repository;
	
	@Autowired
	private MockMvc mockMvc;

	private static File DATA_JSON= Paths.get("src","test","resources","Flights.json").toFile();

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException{

		Flights flights []=new ObjectMapper().readValue(DATA_JSON,Flights[].class);

		Arrays.stream(flights).forEach(repository::save);
	
	}

	@AfterEach
	public void cleanUp() {
	
		repository.deleteAll();
	
	}

	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/findAll
	@Test
	@DisplayName("Test Get All Filghts- GET /admin/flights/findAll")
	public void testfindAllFlights() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/findAll"))
		//Validate status should be 
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		//Validate Response Body
		.andExpect(jsonPath("$[0].Flight_number", is(1)))
		.andExpect(jsonPath("$[0].Flight_name", is("QATT")))
		.andExpect(jsonPath("$[0].Associated_airline", is("QattarAirways")))
		.andExpect(jsonPath("$[0].Class_available", is("Business")))
		.andExpect(jsonPath("$[0].number_of_seats", is(100)))
		
		.andExpect(jsonPath("$[1].Flight_number", is(2)))
		.andExpect(jsonPath("$[1].Flight_name", is("AIR")))
		.andExpect(jsonPath("$[1].Associated_airline", is("AirIndia")))
		.andExpect(jsonPath("$[1].Class_available", is("Economy")))
		.andExpect(jsonPath("$[1].number_of_seats", is(300)));
		
	}
	
	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/find/{Flight_number}
	@Test
	@DisplayName("Test Find Flight by Flight_number - GET /admin/flights/find/{Flight_number}")
	public void testfindFlightsByFlightNumber() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Flight_number}",1))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(1)))
		.andExpect(jsonPath("$.Flight_name", is("QATT")))
		.andExpect(jsonPath("$.Associated_airline", is("QattarAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
//		.andExpect(jsonPath("$[0].Flight_number", is(2)))
//		.andExpect(jsonPath("$[0].Flight_name", is("AIR")))
//		.andExpect(jsonPath("$[0].Associated_airline", is("AirIndia")))
//		.andExpect(jsonPath("$[0].Class_available", is("Economy")))
//		.andExpect(jsonPath("$[0].number_of_seats", is(300)));
	}

	@Test
	@DisplayName("Test Find Flight by Associated_airline - GET /admin/flights/find/{Associated_airline}")
	public void testfindFlightsByAssociatedAirline() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Associated_airline}","QattarAirways"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(1)))
		.andExpect(jsonPath("$.Flight_name", is("QATT")))
		.andExpect(jsonPath("$.Associated_airline", is("QattarAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
//		.andExpect(jsonPath("$[0].Flight_number", is(2)))
//		.andExpect(jsonPath("$[0].Flight_name", is("AIR")))
//		.andExpect(jsonPath("$[0].Associated_airline", is("AirIndia")))
//		.andExpect(jsonPath("$[0].Class_available", is("Economy")))
//		.andExpect(jsonPath("$[0].number_of_seats", is(300)));
	}
	
	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/find/{Flight_name}
	@Test
	@DisplayName("Test Find Flight by Fight_name - GET /admin/flights/find/{Flight_name}")
	public void testfindFlightsByFlightName() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Flight_name}","QATT"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(1)))
		.andExpect(jsonPath("$.Flight_name", is("QATT")))
		.andExpect(jsonPath("$.Associated_airline", is("QattarAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
//		.andExpect(jsonPath("$[0].Flight_number", is(2)))
//		.andExpect(jsonPath("$[0].Flight_name", is("AIR")))
//		.andExpect(jsonPath("$[0].Associated_airline", is("AirIndia")))
//		.andExpect(jsonPath("$[0].Class_available", is("Economy")))
//		.andExpect(jsonPath("$[0].number_of_seats", is(300)));
	}

	
	//Admin should be able to add a flight
	//http://localhost:8888/admin/flights/add
	@Test
	@DisplayName("Test Add New Flight - POST /admin/flights/add")
	public void testaddNewFlight() throws Exception{

		Flights newFlight = new Flights(3,"BRIT","BritishAirways","Business",100);
		newFlight.setFlight_number(3);
		newFlight.setFlight_name("BRIT");
		newFlight.setAssociated_airline("BritishAirways");
		newFlight.setClass_available("Business");
		newFlight.setNumber_of_seats(100);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/flights/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newFlight)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(3)))
		.andExpect(jsonPath("$.Flight_name", is("BRIT")))
		.andExpect(jsonPath("$.Associated_airline", is("BritishAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));

	}
	
	//Admin should be able to add a flight
	//http://localhost:8888/admin/flights/add/bulk
	@Test
	@DisplayName("Test Bulk Insert New Flights - POST /admin/flights/add/bulk")
	public void testbulkFlightInsert() throws Exception{

		Flights newFlight1 = new Flights(3,"BRIT","BritishAirways","Business",100);
		newFlight1.setFlight_number(3);
		newFlight1.setFlight_name("BRIT");
		newFlight1.setAssociated_airline("BritishAirways");
		newFlight1.setClass_available("Business");
		newFlight1.setNumber_of_seats(100);
		
		Flights newFlight2 = new Flights(4,"AMR","AmericanAirlines","Business",100);
		newFlight2.setFlight_number(4);
		newFlight2.setFlight_name("AMR");
		newFlight2.setAssociated_airline("AmericanAirlines");
		newFlight2.setClass_available("Business");
		newFlight2.setNumber_of_seats(100);
		
		List<Flights> newFlights = new ArrayList<>();
		newFlights.add(newFlight1);
		newFlights.add(newFlight2);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/flights/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newFlights)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$[0].Flight_number", is(3)))
		.andExpect(jsonPath("$[0].Flight_name", is("BRIT")))
		.andExpect(jsonPath("$[0].Associated_airline", is("BritishAirways")))
		.andExpect(jsonPath("$[0].Class_available", is("Business")))
		.andExpect(jsonPath("$[0].number_of_seats", is(100)))
		
		.andExpect(jsonPath("$[1].Flight_number", is(4)))
		.andExpect(jsonPath("$[1].Flight_name", is("AMR")))
		.andExpect(jsonPath("$[1].Associated_airline", is("AmericanAirlines")))
		.andExpect(jsonPath("$[1].Class_available", is("Business")))
		.andExpect(jsonPath("$[1].number_of_seats", is(100)));

	}

	//Admin should be able to update a flight
	//http://localhost:8888/admin/airlines/update/{flight_name}
	

}
