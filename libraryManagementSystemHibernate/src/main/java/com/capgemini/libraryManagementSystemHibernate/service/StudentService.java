package com.capgemini.libraryManagementSystemHibernate.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.libraryManagementSystemHibernate.dto.BookBean;
import com.capgemini.libraryManagementSystemHibernate.dto.BookIssueDetails;
import com.capgemini.libraryManagementSystemHibernate.dto.BorrowedBooks;
import com.capgemini.libraryManagementSystemHibernate.dto.RequestDetails;
import com.capgemini.libraryManagementSystemHibernate.dto.StudentsBean;

public interface StudentService {
	
	boolean register(StudentsBean student);
	StudentsBean login(String email,String password);
	boolean addBook(BookBean book);
	boolean removeBook(int bId);
	boolean updateBook(BookBean book);
	boolean issueBook(int bId,int sId);
	boolean request(int sId, int bId);
	List<BorrowedBooks> borrowedBook(int sId);
	LinkedList<BookBean> searchBookById(int bId);
	LinkedList<BookBean> searchBookByTitle(String bookName);
	LinkedList<BookBean> searchBookByAuthor(String author);
	LinkedList<BookBean> getBooksInfo();
	boolean returnBook(int bId,int sId,String status);	
	LinkedList<BookIssueDetails> bookHistoryDetails(int sId);
	LinkedList<RequestDetails> showRequests();
	LinkedList<BookIssueDetails> showIssuedBooks();
	LinkedList<StudentsBean> showStudents();
	boolean updatePassword(String email,String password,String newPassword,String role);
}

