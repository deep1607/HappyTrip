package com.myapp.spring.admin.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.myapp.spring.admin.model.Flights;
import com.myapp.spring.admin.repository.FlightsRepository;

@Service
public class FlightService {

	@Autowired
	private FlightsRepository repository;
	
	
	public Flights updateFlight(Flights flight) {
		
		String id = flight.getFlightName();
		
		Flights oldflight = repository.findByFlightName(id).get();
		
		BeanUtils.copyProperties(flight, oldflight);
		
		return repository.save(oldflight);
	}
	
	
}
