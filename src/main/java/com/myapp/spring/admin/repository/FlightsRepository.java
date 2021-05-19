package com.myapp.spring.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.admin.model.Flights;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Integer>{
	
	Optional <Flights> findByFlightName(String flightName);
	

}
