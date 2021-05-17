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
import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightsAPITest {
	
	@MockBean
	private FlightsRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/findAll
	@Test
	@DisplayName("Test Find All Flights- GET /admin/flights/findAll")
	public void testfindAllAirlines() throws Exception{

		Flights flight1 = new Flights(1,"QATT","QattarAirways","Business",100);
		flight1.setFlight_number(1);
		flight1.setFlight_name("QATT");
		flight1.setAssociated_airline("QattarAirways");
		flight1.setClass_available("Business");
		flight1.setNumber_of_seats(100);
		
		Flights flight2 = new Flights(2,"AIR","AirIndia","Economy",300);
		flight2.setFlight_number(42);
		flight2.setFlight_name("AIR");
		flight2.setAssociated_airline("AirIndia");
		flight2.setClass_available("Economy");
		flight2.setNumber_of_seats(300);
		
		List<Flights> flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		
		doReturn(flights).when(repository).findAll();
		
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
	@DisplayName("Test Find Flight By Flight_number - GET /admin/flights/find/{Flight_number}")
	public void testfindFlightsByFlightNumber() throws Exception{

		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		flight.setFlight_number(3);
		flight.setFlight_name("BRIT");
		flight.setAssociated_airline("BritishAirways");
		flight.setClass_available("Business");
		flight.setNumber_of_seats(100);
		
		doReturn(Optional.of(flight)).when(repository).findByAssociated_airline(flight.getAssociated_airline());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Flight_number}",3))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(3)))
		.andExpect(jsonPath("$.Flight_name", is("BRIT")))
		.andExpect(jsonPath("$.Associated_airline", is("BritishAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
	}

	//Admin is able to view all flights
	//http://localhost:8888/admin/flights/find/{Associated_airline}
	@Test
	@DisplayName("Test Find Flights By Associated_airlines - GET /admin/flights/find/{Associated_airline}")
	public void testfindFlightsByAssociatedAirline() throws Exception{

		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		flight.setFlight_number(3);
		flight.setFlight_name("BRIT");
		flight.setAssociated_airline("BritishAirways");
		flight.setClass_available("Business");
		flight.setNumber_of_seats(100);
		
		doReturn(Optional.of(flight)).when(repository).findByFlight_name(flight.getFlight_name());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Associated_airline}}","BritishAirways"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(3)))
		.andExpect(jsonPath("$.Flight_name", is("BRIT")))
		.andExpect(jsonPath("$.Associated_airline", is("BritishAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
	}
	
	//Admin is able to view all flights
	//http://localhost:8888/admin/flightss/find/{Flight_name}
	@Test
	@DisplayName("Test Find Flights By Flight_name - GET /admin/flights/find/{Flight_name}")
	public void testfindFlightsByName() throws Exception{

		Flights flight = new Flights(3,"BRIT","BritishAirways","Business",100);
		flight.setFlight_number(3);
		flight.setFlight_name("BRIT");
		flight.setAssociated_airline("BritishAirways");
		flight.setClass_available("Business");
		flight.setNumber_of_seats(100);
		
		doReturn(Optional.of(flight)).when(repository).findByFlight_name(flight.getFlight_name());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/flights/find/{Flight_name}","BRIT"))
		
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(3)))
		.andExpect(jsonPath("$.Flight_name", is("BRIT")))
		.andExpect(jsonPath("$.Associated_airline", is("BritishAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));
		
	}
	
		
	//Admin should be able to add an flight
	//http://localhost:8888/admin/airlines/add
	@Test
	@DisplayName("Test Add New Airline - POST /admin/flights/add")
	public void testaddNewFlight() throws Exception{

		Flights newFlight = new Flights(1,"QATT","QattarAirways","Business",100);
		newFlight.setFlight_number(1);
		newFlight.setFlight_name("QATT");
		newFlight.setAssociated_airline("QattarAirways");
		newFlight.setClass_available("Business");
		newFlight.setNumber_of_seats(100);
		
		Flights mockFlight = new Flights(1,"QATT","QattarAirways","Business",100);
		mockFlight.setFlight_number(1);
		mockFlight.setFlight_name("QATT");
		mockFlight.setAssociated_airline("QattarAirways");
		mockFlight.setClass_available("Business");
		mockFlight.setNumber_of_seats(100);
		
		doReturn(mockFlight).when(repository).save(ArgumentMatchers.any());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/flights/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newFlight)))
		
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		.andExpect(jsonPath("$.Flight_number", is(1)))
		.andExpect(jsonPath("$.Flight_name", is("QATT")))
		.andExpect(jsonPath("$.Associated_airline", is("QattarAirways")))
		.andExpect(jsonPath("$.Class_available", is("Business")))
		.andExpect(jsonPath("$.number_of_seats", is(100)));

	}

	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add/bulk
	@Test
	@DisplayName("Test Bulk Insert New Flight - POST /admin/flights/add/bulk")
	public void testbulkAirlineInsert() throws Exception{

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
		
		Flights mockFlight1 = new Flights(3,"BRIT","BritishAirways","Business",100);
		mockFlight1.setFlight_number(3);
		mockFlight1.setFlight_name("BRIT");
		mockFlight1.setAssociated_airline("BritishAirways");
		mockFlight1.setClass_available("Business");
		mockFlight1.setNumber_of_seats(100);
		
		Flights mockFlight2 = new Flights(4,"AMR","AmericanAirlines","Business",100);
		mockFlight2.setFlight_number(4);
		mockFlight2.setFlight_name("AMR");
		mockFlight2.setAssociated_airline("AmericanAirlines");
		mockFlight2.setClass_available("Business");
		mockFlight2.setNumber_of_seats(100);
		
		List<Flights> mockFlights = new ArrayList<>();
		mockFlights.add(mockFlight1);
		mockFlights.add(mockFlight2);
		
		doReturn(mockFlights).when(repository).save(ArgumentMatchers.any());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/flights/add/bulk")
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

	

}
