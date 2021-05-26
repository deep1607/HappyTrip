package com.myapp.spring.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.spring.admin.model.Airlines;


@Repository
public interface AirlineRepository extends JpaRepository<Airlines, Integer>{
	

	
	@Query(value="SELECT * FROM airlines ORDER BY Airline_code ASC",nativeQuery=true)
	public List<Airlines> viewairline();
	
	Optional <Airlines> findByAirlineName(String airlineName);
	
	Optional <Airlines> findByAirlineCode(String airlineCode);	

}
