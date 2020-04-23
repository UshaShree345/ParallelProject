package com.capgemini.libraryManagementSystemJdbc.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RequestDetails implements Serializable {
	
	private int sId;
	private String fullName;
	private int bId;
	private String bookName;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}