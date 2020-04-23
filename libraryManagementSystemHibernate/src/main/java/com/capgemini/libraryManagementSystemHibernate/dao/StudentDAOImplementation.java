package com.capgemini.libraryManagementSystemHibernate.dao;

import java.util.LinkedList;
import java.util.List;

public class StudentDAOImplementation implements StudentDAO {

	@Override
	public boolean register(com.capgemini.libraryManagementSystemHibernate.dto.StudentsBean student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public com.capgemini.libraryManagementSystemHibernate.dto.StudentsBean login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBook(com.capgemini.libraryManagementSystemHibernate.dto.BookBean book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBook(int bId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(com.capgemini.libraryManagementSystemHibernate.dto.BookBean book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean issueBook(int bId, int sId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean request(int sId, int bId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<com.capgemini.libraryManagementSystemHibernate.dto.BorrowedBooks> borrowedBook(int sId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookBean> searchBookById(int bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookBean> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookBean> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookBean> getBooksInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnBook(int bId, int sId, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookIssueDetails> bookHistoryDetails(int sId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.RequestDetails> showRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.BookIssueDetails> showIssuedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<com.capgemini.libraryManagementSystemHibernate.dto.StudentsBean> showStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 }
