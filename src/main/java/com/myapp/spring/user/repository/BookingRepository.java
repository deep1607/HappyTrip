package com.myapp.spring.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.user.model.Booking;


@Repository
public interface BookingRepository extends JpaRepository < Booking, Integer > {
	
	//Optional<Booking> findByfromcityAndtocityAnddate(String fromcity,String tocity,  Date date);
	
   
}

