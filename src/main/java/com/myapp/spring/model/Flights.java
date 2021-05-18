package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name="Flights")

public class Flights {
	
	@Id
	@Column(name="Flight_number",nullable=false,unique = true)
	private Number Flight_number;	

	@Column(name="Flight_name",nullable=false,unique = true)
	private String Flight_name;
	
	@Column(name="Associated_airline",nullable=false,unique = true)
	private String Associated_airline;
	
	@Column(name="Class_available",nullable=false)
	private String Class_available;

	@Column(name="number_of_seats",nullable=false)
	private Number number_of_seats;

	public Flights(Number flight_number, String flight_name, String associated_airline, String class_available,
			Number number_of_seats) {
		Flight_number = flight_number;
		Flight_name = flight_name;
		Associated_airline = associated_airline;
		Class_available = class_available;
		this.number_of_seats = number_of_seats;
	}
	
	public Flights() {
		// TODO Auto-generated constructor stub
	}

	public Number getFlight_number() {
		return Flight_number;
	}

	public void setFlight_number(Number flight_number) {
		Flight_number = flight_number;
	}

	public String getFlight_name() {
		return Flight_name;
	}

	public void setFlight_name(String flight_name) {
		Flight_name = flight_name;
	}

	public String getAssociated_airline() {
		return Associated_airline;
	}

	public void setAssociated_airline(String associated_airline) {
		Associated_airline = associated_airline;
	}

	public String getClass_available() {
		return Class_available;
	}

	public void setClass_available(String class_available) {
		Class_available = class_available;
	}

	public Number getNumber_of_seats() {
		return number_of_seats;
	}

	public void setNumber_of_seats(Number number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Associated_airline == null) ? 0 : Associated_airline.hashCode());
		result = prime * result + ((Class_available == null) ? 0 : Class_available.hashCode());
		result = prime * result + ((Flight_name == null) ? 0 : Flight_name.hashCode());
		result = prime * result + ((Flight_number == null) ? 0 : Flight_number.hashCode());
		result = prime * result + ((number_of_seats == null) ? 0 : number_of_seats.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flights other = (Flights) obj;
		if (Associated_airline == null) {
			if (other.Associated_airline != null)
				return false;
		} else if (!Associated_airline.equals(other.Associated_airline))
			return false;
		if (Class_available == null) {
			if (other.Class_available != null)
				return false;
		} else if (!Class_available.equals(other.Class_available))
			return false;
		if (Flight_name == null) {
			if (other.Flight_name != null)
				return false;
		} else if (!Flight_name.equals(other.Flight_name))
			return false;
		if (Flight_number == null) {
			if (other.Flight_number != null)
				return false;
		} else if (!Flight_number.equals(other.Flight_number))
			return false;
		if (number_of_seats == null) {
			if (other.number_of_seats != null)
				return false;
		} else if (!number_of_seats.equals(other.number_of_seats))
			return false;
		return true;
	}



	
}


