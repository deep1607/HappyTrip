package com.myapp.spring.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.Flights;


@SpringBootTest
public class FlightsRepositoryTest {

	@Autowired
	private FlightsRepository repository;

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

	@Test
	@DisplayName("Test Flights not found for a non existing airline_code")
	public void testFlightsNotFoundForNonExistingCode() {
	
		Flights flights = repository.findById(5).orElseGet(() -> new Flights());

		Assertions.assertNull(flights.getFlight_number(), "Flight with Id 5 does not exist");
	
	}
	

	@Test
	@DisplayName("Test Flights saved successfully")
	public void testAirlinesSavedSuccessfully() {
	
		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		flight.setFlight_number(3);
		flight.setFlight_name("BRIT");
		flight.setAssociated_airline("BritishAirways");
		flight.setClass_available("Business");
		flight.setNumber_of_seats(100);
		

		Flights savedFlight = repository.save(flight);

		Assertions.assertNotNull(savedFlight,"New Flight should be saved");
	
		Assertions.assertNotNull(savedFlight.getFlight_number(),"New Flight should have Number");
		
		Assertions.assertEquals(flight.getFlight_name(), savedFlight.getFlight_name());
		Assertions.assertEquals(flight.getFlight_number(), savedFlight.getFlight_number());
		Assertions.assertEquals(flight.getAssociated_airline(), savedFlight.getAssociated_airline());
		Assertions.assertEquals(flight.getClass_available(), savedFlight.getClass_available());
		Assertions.assertEquals(flight.getNumber_of_seats(), savedFlight.getNumber_of_seats());
	
	}
	
	@Test
	@DisplayName("Test Flight updated successfully")
	public void testFlightsUpdatedSuccessfully() {
	
		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		flight.setFlight_number(3);
		flight.setFlight_name("BRIT");
		flight.setAssociated_airline("BritishAirways");
		flight.setClass_available("Business");
		flight.setNumber_of_seats(100);
		

		Flights updatedFlight = repository.save(flight);
		
		Assertions.assertEquals(flight.getFlight_name(), updatedFlight.getFlight_name());
		Assertions.assertEquals(flight.getFlight_number(), updatedFlight.getFlight_number());
		Assertions.assertEquals(flight.getAssociated_airline(), updatedFlight.getAssociated_airline());
		Assertions.assertEquals(flight.getClass_available(), updatedFlight.getClass_available());
		Assertions.assertEquals(flight.getNumber_of_seats(), updatedFlight.getNumber_of_seats());
	}
	
	
}