package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RequestInfo implements Serializable {
	
	private BooksInfo bookInfo;
	private StudentInfo StudentInfo;
	private boolean isIssued;
	private boolean isReturned;
	private LocalDate issuedDate;
	private LocalDate returnedDate;
	
	public BooksInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BooksInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public StudentInfo getStudentInfo() {
		return StudentInfo;
	}
	public void setStudentInfo(StudentInfo StudentInfo) {
		this.StudentInfo = StudentInfo;
	}
	public boolean isIssued() {
		return isIssued;
	}
	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}
	public boolean isReturned() {
		return isReturned;
	}
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public LocalDate getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}
}
