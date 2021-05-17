package com.myapp.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myapp.spring.model.Airlines;

@Repository
public interface AirlineRepository extends JpaRepository<Airlines, String>{
	
	//Admin is able to view all airlines
	List<Airlines> findAll();
	
	//Admin should be able to view an airline
	//select * from Airlines where Airline_name=?	
	Optional<Airlines> findByAirline_name(String airline_name);

	//Admin should be able to view an airline
	//select * from Airlines where Airline_code=?	
	Optional<Airlines> findByAirline_code(String airline_code);	

	
//    //Admin should be able to update an airline
//    @Query("UPDATE Airlines a set a.Airline_name = :ar_name WHERE a.Airline_code = :ar_code")
//    void updateAirline_name(@Param("ar_code") String airline_code, @Param("ar_name") String airline_name);
//    
//    //Admin should be able to update an airline
//    @Query("UPDATE Airlines a set a.Airline_code = :ar_code WHERE a.Airline_name = :ar_name")
//    void updateAirline_code(@Param("ar_name") String airline_name, @Param("ar_code") String airline_code);

    
//	//Admin should be able to add an airline
//	//INSERT INTO Airlines (Airline_code,Airline_name) VALUES (?,?) 
//	 @Query("INSERT INTO Airlines (Airline_code,Airline_name) VALUES (?,?)"
//	 .setParameter(1, Airlines.getAirline_code())
//     .setParameter(2, Airlines.getAirline_name())
//     .executeUpdate();
//     void setCustomerName(@Param("ar_code") String ar_code, @Param("ar_name") String ar_name);
     
}
