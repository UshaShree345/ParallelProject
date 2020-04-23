package com.capgemini.libraryManagementSystemJdbc.dto;

import java.io.Serializable;
import java.util.Date;

public class BookIssueDetails implements Serializable {
	
	private int bookId;
	private int studentId;
	private Date issueDate;
	private Date returnDate;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}	
}
