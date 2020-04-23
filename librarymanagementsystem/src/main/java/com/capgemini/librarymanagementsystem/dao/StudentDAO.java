package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.StudentInfo;

public interface StudentDAO {
	
	boolean registerStudent(StudentInfo student);
	StudentInfo authenticateStudent(String email,String password);
	public RequestInfo bookRequest(StudentInfo student, BooksInfo book);
	public RequestInfo bookReturn(StudentInfo student, BooksInfo book);
	LinkedList<BooksInfo> searchBookByTitle(String bookName);
	LinkedList<BooksInfo> searchBookByAuthor(String author);
	LinkedList<BooksInfo> searchBookByCategory(String category);
	LinkedList<BooksInfo> getBooksInfo();
}
