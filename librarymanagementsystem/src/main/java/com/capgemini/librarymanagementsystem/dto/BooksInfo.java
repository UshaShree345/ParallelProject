package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class BooksInfo implements Serializable {
	
	private int bookId;
	private String bookName;
	private String author;
	private String category;
	private String publishername;
	private String bookIssuedate;
	private String bookReturndate;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublishername() {
		return publishername;
	}
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}
	public String getBookIssuedate() {
		return bookIssuedate;
	}
	public void setBookIssuedate(String bookIssuedate) {
		this.bookIssuedate = bookIssuedate;
	}
	public String getBookReturndate() {
		return bookReturndate;
	}
	public void setBookReturndate(String bookReturndate) {
		this.bookReturndate = bookReturndate;
	}	
}