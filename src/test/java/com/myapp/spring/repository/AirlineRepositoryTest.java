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
import com.myapp.spring.model.Airlines;


@SpringBootTest
public class AirlineRepositoryTest {

	@Autowired
	private AirlineRepository repository;

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

	@Test
	@DisplayName("Test Airlines not found for a non existing airline_code")
	public void testAirlinesNotFoundForNonExistingCode() {
	
		Airlines airlines=repository.findByAirline_code("OO12").orElseGet(() -> new Airlines());

		Assertions.assertNull(airlines.getAirline_code(), "Airline with Code OO12 does not exist");
	
	}
	

	@Test
	@DisplayName("Test product saved successfully")
	public void testAirlinesSavedSuccessfully() {
	
		Airlines airlines = new Airlines("QA12","QattarAirways");
		airlines.setAirline_code("QA12");
		airlines.setAirline_name("QattarAirways");

		Airlines savedAirlines = repository.save(airlines);

		Assertions.assertNotNull(savedAirlines,"New Airlines should be saved");
	
		Assertions.assertNotNull(savedAirlines.getAirline_code(),"New Airlines should have Code");
		
		Assertions.assertEquals(airlines.getAirline_name(), savedAirlines.getAirline_name());;
	
	}
	
	@Test
	@DisplayName("Test product updated successfully")
	public void testAirlinesUpdatedSuccessfully() {
	
		Airlines airlines = new Airlines("QA12","QattarAirways");
		airlines.setAirline_code("QA12");
		airlines.setAirline_name("QattarAirways");
		
		Airlines updatedAirlines = repository.save(airlines);
		
		Assertions.assertEquals(airlines.getAirline_name(), updatedAirlines.getAirline_name());
		Assertions.assertEquals(airlines.getAirline_code(), updatedAirlines.getAirline_code());
	}
	
	
}