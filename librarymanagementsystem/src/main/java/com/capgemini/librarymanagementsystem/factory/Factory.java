package com.capgemini.librarymanagementsystem.factory;

import com.capgemini.librarymanagementsystem.dao.LibrarianDAO;
import com.capgemini.librarymanagementsystem.dao.LibrarianDAOImplementation;
import com.capgemini.librarymanagementsystem.dao.StudentDAO;
import com.capgemini.librarymanagementsystem.dao.StudentDAOImplementation;
import com.capgemini.librarymanagementsystem.service.LibrarianService;
import com.capgemini.librarymanagementsystem.service.LibrarianServiceImplementation;
import com.capgemini.librarymanagementsystem.service.StudentService;
import com.capgemini.librarymanagementsystem.service.StudentServiceImplementation;

public class Factory {
	
	public static LibrarianDAO getLibrarianDAO() {
		return new LibrarianDAOImplementation();
	}
	
	public static LibrarianService getLibrarianService() {
		return new LibrarianServiceImplementation();
	}
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
}
