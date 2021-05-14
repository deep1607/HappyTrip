package com.myapp.spring.model;

	import java.util.Objects;

import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name="user")

	public class Login { 

		
		@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="UserName")
		private String UserName;
		
		
		@Column(name="password")
		private String password;
		

		public String getUserName() {
			return UserName;
		}

		public void setUserName(String userName) {
			UserName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Login(String userName, String password) {
			super();
			UserName = userName;
			this.password = password;
		}
		
		
		
		
		
		
		
}
