package com.capgemini.libraryManagementSystemHibernate.factory;

import com.capgemini.libraryManagementSystemHibernate.dao.StudentDAO;
import com.capgemini.libraryManagementSystemHibernate.dao.StudentDAOImplementation;
import com.capgemini.libraryManagementSystemHibernate.service.StudentService;
import com.capgemini.libraryManagementSystemHibernate.service.StudentServiceImplementation;

public class Factory {
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImplementation();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImplementation();
	}
}
	

