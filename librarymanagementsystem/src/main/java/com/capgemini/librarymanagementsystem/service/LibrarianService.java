package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.LibrarianInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.StudentInfo;

public interface LibrarianService {
	
	boolean registerLibrarian(LibrarianInfo librarian);
	LibrarianInfo authenticateLibrarian(String email,String password);
	boolean addBook(BooksInfo book);
	boolean removeBook(int id);
	boolean updateBook(BooksInfo book);
	LinkedList<BooksInfo> searchBookByTitle(String bookname);
	LinkedList<BooksInfo> searchBookByAuthor(String author);
	LinkedList<BooksInfo> searchBookByCategory(String category);
	LinkedList<BooksInfo> getBooksInfo();
	List<StudentInfo> showStudents();
	List<RequestInfo> showRequests();
	boolean bookIssue(StudentInfo student,BooksInfo book);
	boolean isBookReceived(StudentInfo student,BooksInfo book);
}
