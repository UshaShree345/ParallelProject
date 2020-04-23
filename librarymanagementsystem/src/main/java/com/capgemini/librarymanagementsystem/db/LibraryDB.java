package com.capgemini.librarymanagementsystem.db;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dto.LibrarianInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.StudentInfo;

public class LibraryDB {
	
	public static final LinkedList<BooksInfo> BOOKS=new LinkedList<BooksInfo>();
	public static final LinkedList<StudentInfo> STUDENT=new LinkedList<StudentInfo>();
	public static final LinkedList<LibrarianInfo> LIBRARIAN=new LinkedList<LibrarianInfo>();
	public static final LinkedList<RequestInfo> REQUEST = new LinkedList<RequestInfo>();
}
