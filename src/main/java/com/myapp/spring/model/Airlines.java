package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Airlines")

public class Airlines {
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Id
	@Column(name="Airline_code",nullable=false,unique = true)
	private String Airline_code;
	
	@Column(name="Airline_name",nullable=false,unique = true)
	private String Airline_name;

//	@Column(name="Airline_logo",nullable=false,unique = true)
//	private String Airline_logo;

	
	
	public Airlines(String airline_code, String airline_name) {
		Airline_code = airline_code;
		Airline_name = airline_name;
//		Airline_logo = airline_logo;
	}

	public Airlines() {
		// TODO Auto-generated constructor stub
	}

	public String getAirline_code() {
		return Airline_code;
	}

	public void setAirline_code(String airline_code) {
		Airline_code = airline_code;
	}

	public String getAirline_name() {
		return Airline_name;
	}

	public void setAirline_name(String airline_name) {
		Airline_name = airline_name;
	}

//	public String getAirline_logo() {
//		return Airline_logo;
//	}
//
//	public void setAirline_logo(String airline_logo) {
//		Airline_logo = airline_logo;
//	}




}
