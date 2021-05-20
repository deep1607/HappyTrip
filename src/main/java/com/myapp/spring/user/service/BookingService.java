package com.myapp.spring.user.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.spring.user.model.Booking;
import com.myapp.spring.user.model.Search;
import com.myapp.spring.user.repository.BookingRepository;
import com.myapp.spring.user.repository.SearchRepository;



@Service
public class BookingService {
	
	@Autowired
	private SearchRepository srepository;

	@Autowired
	private BookingRepository brepository;
	
	public Booking bookingCheck(Booking bookingInfo) {

		String tocity= bookingInfo.getTocity();
		 String fromcity=bookingInfo.getFromcity();
		Date date = bookingInfo.getDate();
		int seats = bookingInfo.getSeats();
		int flightno =bookingInfo.getFlightNo();
		
		Search search = null;
		if(srepository.checkingSeatAvail(fromcity, tocity, date, seats,flightno).isPresent()) {
			search=srepository.checkingSeatAvail(fromcity, tocity, date, seats,flightno).orElseGet(Search::new);
		}
			
		if(search==null) {
			return  new Booking(0, "", "", "", "", null,0, 0);
		}
		
		
		return brepository.save(bookingInfo);
	}

}