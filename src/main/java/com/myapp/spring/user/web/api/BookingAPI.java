package com.myapp.spring.user.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.user.model.Booking;
import com.myapp.spring.user.service.BookingService;


	@RestController
	@RequestMapping("user/booking")
	public class BookingAPI {

		@Autowired
		private BookingService service;
		
		@PostMapping
		public ResponseEntity<Booking> booking(@RequestBody Booking bookingInfo ){
			
			return new ResponseEntity<>(service.bookingCheck(bookingInfo),HttpStatus.OK);
		}
		
	
	     
	}
		
		
