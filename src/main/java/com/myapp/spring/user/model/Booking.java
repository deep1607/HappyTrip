package com.myapp.spring.user.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date; 

	@Entity
	@Table(name="booking_table")
	public class Booking {
		
		@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="Booking_ID")
		private int id;
		
		@Column(name = "Name")
		private String name;

		@Column(name = "Date_Of_Birth",nullable = false)
		private String dateOfBirth;
		
		@Column(name = "from_city")
		private String fromcity;
		
		@Column(name = "to_city")
		private String tocity;
		
		@Column(name = "Date")
		private Date date;
		
		@Column(name = "seats")
		private int seats;
		
		@Column(name = "Flight_No")
		private int flightNo;
		
		
		public Booking(int id, String name, String dateOfBirth, String fromcity, String tocity, Date date, int seats,
				int flightNo) {
			this.id = id;
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.fromcity = fromcity;
			this.tocity = tocity;
			this.date = date;
			this.seats = seats;
			this.flightNo = flightNo;
		}

		public Booking() {
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getFromcity() {
			return fromcity;
		}

		public void setFromcity(String fromcity) {
			this.fromcity = fromcity;
		}

		public String getTocity() {
			return tocity;
		}

		public void setTocity(String tocity) {
			this.tocity = tocity;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getSeats() {
			return seats;
		}

		public void setSeats(int seats) {
			this.seats = seats;
		}

		public int getFlightNo() {
			return flightNo;
		}

		public void setFlightNo(int flightNo) {
			this.flightNo = flightNo;
		}

	}

		