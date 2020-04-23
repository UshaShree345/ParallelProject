package com.capgemini.libraryManagementSystemHibernate.dto;

import java.io.Serializable;
import java.util.Date;

public class StudentsBean implements Serializable {
	
	private int sId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long mobile;
	private String role;
	
	public int getSId() {
		return sId;
	}
	public void setSId(int sId) {
		this.sId = sId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}		
}
