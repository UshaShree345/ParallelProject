package com.capgemini.libraryManagementSystemHibernate.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.libraryManagementSystemHibernate.dto.BookBean;
import com.capgemini.libraryManagementSystemHibernate.dto.BookIssueDetails;
import com.capgemini.libraryManagementSystemHibernate.dto.BorrowedBooks;
import com.capgemini.libraryManagementSystemHibernate.dto.RequestDetails;
import com.capgemini.libraryManagementSystemHibernate.dto.StudentsBean;

public class StudentDAOImplementation implements StudentDAO {

	@Override
	public boolean register(StudentsBean student) {
		
		return false;
	}

	@Override
	public StudentsBean login(String email, String password) {
		
		return null;
	}

	@Override
	public boolean addBook(BookBean book) {
	
		return false;
	}

	@Override
	public boolean removeBook(int bId) {
		
		return false;
	}

	@Override
	public boolean updateBook(BookBean book) {
		
		return false;
	}

	@Override
	public boolean issueBook(int bId, int sId) {
		
		return false;
	}

	@Override
	public boolean request(int sId, int bId) {
		
		return false;
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int sId) {
		
		return null;
	}

	@Override
	public LinkedList<BookBean> searchBookById(int bId) {
		
		return null;
	}

	@Override
	public LinkedList<BookBean> searchBookByTitle(String bookName) {
		
		return null;
	}

	@Override
	public LinkedList<BookBean> searchBookByAuthor(String author) {
		
		return null;
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {
		
		return null;
	}

	@Override
	public boolean returnBook(int bId, int sId, String status) {
		
		return false;
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int sId) {
		
		return null;
	}

	@Override
	public LinkedList<RequestDetails> showRequests() {
		
		return null;
	}

	@Override
	public LinkedList<BookIssueDetails> showIssuedBooks() {
		
		return null;
	}

	@Override
	public LinkedList<StudentsBean> showStudents() {
		
		return null;
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		
		return false;
	}

}
