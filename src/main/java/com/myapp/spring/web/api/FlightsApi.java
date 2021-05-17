package com.myapp.spring.web.api; 

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Flights;
import com.myapp.spring.repository.FlightsRepository;

@RestController
@RequestMapping("/admin/flights")
public class FlightsApi {

	//Dependency Injection
	@Autowired
	private FlightsRepository repository;

	//Admin is able to view all flights
	//http://localhost:8888/admin/airlines/findAll
	@GetMapping("/findAll")
	public ResponseEntity<List<Flights>> findAllFlights(){
		return new ResponseEntity<List<Flights>>(repository.findAll(),HttpStatus.OK);
		
	}
	//Admin is able to view a flight
	//http://localhost:8888/admin/airlines/find/{flight_number}
	@GetMapping("/find/{Flight_number}")
	public ResponseEntity<Flights> findFlightsByFlightNumber(@PathVariable("Flight_number") String flight_number){
		return new ResponseEntity<Flights>(repository.findById("flight_number").get(),HttpStatus.OK);
			
	}
	
	//Admin is able to view a flight
	//http://localhost:8888/admin/airlines/find/{flight_name}
	@GetMapping("/find/{Associated_airline}")
	public ResponseEntity<Flights> findFlightsByAssociatedAirline(@PathVariable("Associated_airline") String associated_airline){
		return new ResponseEntity<Flights>(repository.findByAssociated_airline("associated_airline").get(),HttpStatus.OK);
			
	}
	
	//Admin is able to view a flight
	//http://localhost:8888/admin/airlines/find/{flight_name}
	@GetMapping("/find/{Flight_name}")
	public ResponseEntity<Flights> findFlightsByFlightName(@PathVariable("Flight_name") String flight_name){
		return new ResponseEntity<Flights>(repository.findByFlight_name("flight_name").get(),HttpStatus.OK);
			
	}
	
	//Admin should be able to add a flight
	//http://localhost:8888/admin/airlines/add
	@PostMapping("/add")
	public ResponseEntity<Flights> addNewFlight(@RequestBody Flights flight){
		return new ResponseEntity<Flights>(repository.save(flight),HttpStatus.CREATED);
		
	}
	
	//Admin should be able to add multiple flights
	//http://localhost:8888/admin/airlines/add/bulk
	@PostMapping("/add/bulk")
	public ResponseEntity<List<Flights>> bulkFlightInsert(@RequestBody List<Flights> flight){
		return new ResponseEntity<List<Flights>>(repository.saveAll(flight),HttpStatus.CREATED);
			
	}
	
	//Admin should be able to update a flight
	//http://localhost:8888/admin/airlines/update/{flight_name}
	@PutMapping("/update/{flight_name}")
	public ResponseEntity<Flights> updateFlights(@PathVariable("flight_name") String flight_name,
			@RequestBody Flights flight){
			
	Flights existingFlight = repository.findByFlight_name(flight_name).get();	
			
	BeanUtils.copyProperties(flight, existingFlight);
			
	return new ResponseEntity<Flights>(repository.save(existingFlight),
			HttpStatus.CREATED);
			
	}
	


	
	
}
