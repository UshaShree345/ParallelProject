package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.StudentDAO;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.StudentInfo;
import com.capgemini.librarymanagementsystem.factory.Factory;

public class StudentServiceImplementation implements StudentService {
	
	private StudentDAO dao=Factory.getStudentDAO();

	@Override
	public boolean registerStudent(StudentInfo student) {
		return dao.registerStudent(student);
	}

	@Override
	public StudentInfo authenticateStudent(String email, String password) {
		return dao.authenticateStudent(email, password);
	}

	@Override
	public RequestInfo bookRequest(StudentInfo student, BooksInfo book) {
		return dao.bookRequest(student, book);
	}

	@Override
	public RequestInfo bookReturn(StudentInfo student, BooksInfo book) {
		return dao.bookReturn(student, book);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public LinkedList<BooksInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}
}
