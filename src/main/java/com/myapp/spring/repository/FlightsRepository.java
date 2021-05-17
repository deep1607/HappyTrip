package com.myapp.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.Flights;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, String>{
	
	//Admin is able to view all flights
	List<Flights> findAll();
	
	//Admin should be able to view a flight
	Optional<Flights> findById(Number flight_number);
	
	//Admin should be able to view a flight	
	Optional<Flights> findByAssociated_airline(String associated_airline);
	
	//Admin should be able to view a flight
	Optional<Flights> findByFlight_name(String flight_name);	

	//Admin should be able to add a flight
	
	//Admin should be able to update a flight
	

}
