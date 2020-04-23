package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.dto.LibrarianInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.StudentInfo;
import com.capgemini.librarymanagementsystem.exception.CommonException;
import com.capgemini.librarymanagementsystem.factory.Factory;
import com.capgemini.librarymanagementsystem.service.LibrarianService;
import com.capgemini.librarymanagementsystem.service.StudentService;
import com.capgemini.librarymanagementsystem.validation.Validation;

public class LibraryMain {
	
	public static void main(String[] args) {
		doReg();
	}
	
	public static void doReg() {

		boolean flag = false;
		int regId = 0; 
		String regName = null; 
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		int regId1 = 0; 
		String regName1 = null; 
		long regMobile1 = 0;
		String regEmail1 = null;
		String regPassword1 = null;
		int bookId = 0; 
		String bookAuthor = null; 
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;
		String bookIssuedate = null;
		String bookReturndate = null;

		Validation validation = new Validation();

		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("-------------<<< WELCOME TO LIBRARY >>>-----------");
			System.out.println("PRESS-1 TO LIBRARIAN PAGE");
			System.out.println("PRESS-2 TO STUDENT PAGE");
			System.out.println("<------------------------------------------------->");
			int i = scanner.nextInt();
			switch (i) {
			case 1:
				LibrarianService service = Factory.getLibrarianService();
				do{
					System.out.println("<-------------------------------------->");
					System.out.println("PRESS-1 TO LIBRARIAN REGISTER");
					System.out.println("PRESS-2 TO LIBRARIAN LOGIN");
					System.out.println("PRESS-3 TO EXIT");
					System.out.println("<-------------------------------------->");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter 6 digitID to Register as Admin : ");
								regId = scanner.nextInt();
								validation.validatedId(regId);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("ID should consist of only digits");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Name to Register : ");
								regName = scanner.next();
								validation.validatedName(regName);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should consists of only Alphabates");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter MobileNumber to Register : ");
								regMobile = scanner.nextLong();
								validation.validatedMobile(regMobile);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Mobile Number  should consists of only numbers");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Email to Register : ");
								regEmail = scanner.next();
								validation.validatedEmail(regEmail);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter a proper email");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Password to Register : ");
								regPassword = scanner.next();
								validation.validatedPassword(regPassword);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						LibrarianInfo bean = new LibrarianInfo();
						bean.setId(regId);
						bean.setName(regName);
						bean.setMobileNo(regMobile);
						bean.setEmail(regEmail);
						bean.setPassword(regPassword);

						boolean check = service.registerLibrarian(bean);
						if(check) {
							System.out.println("REGISTERED SUCCESSFULLY");
						} else {
							System.out.println("Email already exist");
						}	
						break;

					case 2:
						System.out.println("Enter registered email to login : ");
						String email = scanner.next();
						System.out.println("Enter registered Password to login : ");
						String password = scanner.next();
						try {
							LibrarianInfo authBean = service.authenticateLibrarian(email, password);
							System.out.println("LOGGED IN SUCCESSFULLY");

							do {
								System.out.println("<---------------------------------->");
								System.out.println("PRESS-1 TO ADD BOOKS");
								System.out.println("PRESS-2 TO UPDATE THE BOOK");
								System.out.println("PRESS-3 TO SEARCH BOOK BY AUTHOR");
								System.out.println("PRESS-4 TO SEARCH BOOK BY TITLE");
								System.out.println("PRESS-5 TO SEARCH BOOK BY CATEGORY");
								System.out.println("PRESS-6 TO REMOVE THE BOOK");
								System.out.println("PRESS-7 TO GET INFO ABOUT ALL BOOKS");
								System.out.println("PRESS-8 TO ISSUE THE BOOK");
								System.out.println("PRESS-9 TO VIEW STUDENTS");
								System.out.println("PRESS-10 TO VIEW REQUESTS");
								System.out.println("PRESS-11 TO RECEIVE RETURNED BOOK");
								System.out.println("PRESS-12 TO  RETURN BACK TO MAIN");
								System.out.println("<----------------------------------->");
								int choice1 = scanner.nextInt();
								switch (choice1) {
								case 1:

									do {
										try {
											System.out.println("Enter BookID to add : ");
											bookId = scanner.nextInt();
											validation.validatedId(bookId);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Id should contains only digits");
										} catch (CommonException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									do {
										try {
											System.out.println("Enter BookName : ");
											bookName = scanner.next();
											validation.validatedName(bookName);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("BookName should contains only Alphabets");
										} catch (CommonException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									do {
										try {
											System.out.println("Enter AuthorName : ");
											bookAuthor = scanner.next();
											validation.validatedName(bookAuthor);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("AuthorName should contains only Alphabates");
										} catch (CommonException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									do {
										try {
											System.out.println("Enter Category : ");
											bookCategory = scanner.next();
											validation.validatedName(bookCategory);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Book-Category should contains only Alphabates");
										} catch (CommonException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									do {
										try {
											System.out.println("Enter PublisherName : ");
											bookPublisherName = scanner.next();
											validation.validatedName(bookPublisherName);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("BookPublisherName should contains only Alphabates");
										} catch (CommonException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									BooksInfo bean1 = new BooksInfo();
									bean1.setBookId(bookId);	
									bean1.setBookName(bookName);
									bean1.setAuthor(bookAuthor);
									bean1.setPublishername(bookPublisherName);
									bean1.setCategory(bookCategory);
									boolean check2 = service.addBook(bean1);
									if(check2) {
										System.out.println("Book Added of id = "+bookId);
									} else {
										System.out.println("Book already exist of id = "+bookId);
									}
									break;
									
								case 2:
									System.out.println("Enter the updated id :");
									int bid= scanner.nextInt();
									System.out.println("enter name");
									String title = scanner.next();
									System.out.println("enter author");
									String bauthor = scanner.next();
									System.out.println("enter category");
									String category1 = scanner.next();
									BooksInfo bean2 = new BooksInfo();
									bean2.setBookId(bid);
									bean2.setBookName(title);
									bean2.setAuthor(bauthor);
									bean2.setCategory(category1);
									boolean updated = service.updateBook(bean2);
									if (updated) {
										System.out.println("Book is updated");
									} else {
										System.out.println("Book is not updated");
									}
									break;
									
								case 3:
									System.out.println("Search book by AuthorName : ");
									String author = scanner.next();

									BooksInfo bean3 = new BooksInfo();
									bean3.setAuthor(author);
									List<BooksInfo> bookauthor = service.searchBookByAuthor(author);
									for (BooksInfo bookBean : bookauthor) {
										if (bookBean != null) {
											System.out.println("<-----------------------------------> ");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available written by this author");
										}
									}
									break;
									
								case 4:
									System.out.println("Search book by Title : ");
									String bookTitle = scanner.next();

									BooksInfo bean4 = new BooksInfo();
									bean4.setBookName(bookTitle);
									List<BooksInfo> booktitle = service.searchBookByTitle(bookTitle);
									for (BooksInfo bookBean : booktitle) {	
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available with this title");
										}
									}
									break;
									
								case 5:
									System.out.println("Search book by Book Category : ");
									String category = scanner.next();

									BooksInfo bean5 = new BooksInfo();
									bean5.setCategory(category);
									List<BooksInfo> bookIds = service.searchBookByCategory(category);
									for (BooksInfo bookBean : bookIds) {
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available with this Category");
										}
									}
									break;
									
								case 6:
									System.out.println("Enter the BookId to delete : ");
									int bookId3 = scanner.nextInt();
									if (bookId3 == 0) {
										System.out.println("Please Enter the Valid BookId");
									} else {
										BooksInfo bean6 = new BooksInfo();
										bean6.setBookId(bookId3);
										boolean remove = service.removeBook(bookId3);
										if (remove) {
											System.out.println("The Book is removed of Id = "+bookId3);
										} else {
											System.out.println("The Book is not removed of Id = "+bookId3);
										}
									}
									break;

								case 7:
									LinkedList<BooksInfo> info = service.getBooksInfo();
									for (BooksInfo bookBean : info) {
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("Books info is null");
										}
									}
									break;
									
								case 8:
									StudentInfo StudentBean2 = new StudentInfo();
									BooksInfo bookBean2 = new BooksInfo();
									System.out.println("enter Book Id");
									int bookId4 = scanner.nextInt();
									System.out.println("enter Student Id");
									int uId = scanner.nextInt();
									bookBean2.setBookId(bookId4);
									StudentBean2.setId(uId);
									try {
										boolean isIssued = service.bookIssue(StudentBean2, bookBean2);
										if (isIssued) {
											System.out.println("Book Issued of Id = "+bookId4);
										} else {
											System.out.println("Book cannot be issued of Id = "+bookId4);
										}
									} catch (Exception e) {
										System.out.println("Invalid data request book cannot be issued");
									}
									break;
									
								case 9:
									try {
										System.out.println("Students of Library are : ");
										System.out.println("<---------------------------------->");
										List<StudentInfo> StudentInfos = service.showStudents();
										for (StudentInfo infos : StudentInfos) {
											System.out.println("StudentId --> " + infos.getId());
											System.out.println("StudentName --> " + infos.getName());
											System.out.println("StudentEmail--> " + infos.getEmail());
											System.out.println("No Of Books Borrowed by Student--> " + infos.getBooksBorrowed());
										}
									} catch (Exception e) {
										System.out.println("Books are not available in library");
									}
									break;
									
								case 10:
									try {
										System.out.println("Requests for Books are : ");
										System.out.println("<--------------------------------->");
										List<RequestInfo> requestInfos = service.showRequests();
										for (RequestInfo info1 : requestInfos) {
											System.out.println("BookId --> " + info1.getBookInfo().getBookId());
											System.out.println("BookName --> " + info1.getBookInfo().getAuthor());
											System.out.println("StudentId--> " + info1.getStudentInfo().getId());
											System.out.println("StudentName --> " + info1.getStudentInfo().getName());
											System.out.println("BookIssued --> " + info1.isIssued());
											System.out.println("BookReturned--> " + info1.isReturned());
										}
									} catch (Exception e) {
										System.out.println("Books are not available in library");
									}
									break;
									
								case 11:
									System.out.println("Receive Returned Books are : ");
									System.out.println("<----------------------------->");
									System.out.println("Enter Student Id");
									int Student_Id = scanner.nextInt();
									System.out.println("Enter Book Id");
									int bookId5 = scanner.nextInt();

									StudentInfo Student = new StudentInfo();
									BooksInfo book = new BooksInfo();
									Student.setId(Student_Id);;
									book.setBookId(bookId5);
									boolean isReceive = service.isBookReceived(Student, book);
									if(isReceive) {
										System.out.println("Received Returned book of Id = "+bookId5);
									}else {
										System.out.println("Invalid ! Admin unable to receive");
									}
									break;
									
								case 12:
									doReg();
									
								default:
									System.out.println("Invalid Choice");
									break;
								}
							} while (true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}
						break;
						
					case 3:
						doReg();
						break;
						
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} while (true);

			case 2:
				StudentService service1 = Factory.getStudentService();
				do {
					System.out.println("<----------------------------------->");
					System.out.println("PRESS-1 TO STUDENT REGISTER");
					System.out.println("PRESS-2 TO STUDENT LOGIN");
					System.out.println("PRESS-3 TO RETURN BACK TO MAIN");
					System.out.println("<----------------------------------->");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID to register as Student : ");
								regId1 = scanner.nextInt();
								validation.validatedId(regId1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Id should contains only digits");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Name to register : ");
								regName1 = scanner.next();
								validation.validatedName(regName1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should contains only Alphabates");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter MobileNumber to register : ");
								regMobile = scanner.nextLong();
								validation.validatedMobile(regMobile);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Mobile Number  should contains only numbers");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Email to register : ");
								regEmail1 = scanner.next();
								validation.validatedEmail(regEmail1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Email should be proper ");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Password to register : ");
								regPassword1 = scanner.next();
								validation.validatedPassword(regPassword1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						StudentInfo bean1 = new StudentInfo();
						bean1.setId(regId1);
						bean1.setName(regName1);
						bean1.setMobileNo(regMobile1);
						bean1.setEmail(regEmail1);
						bean1.setPassword(regPassword1);

						boolean check = service1.registerStudent(bean1);
						if (check) {
							System.out.println("REGISTERED SUCCESSFULLY");
						} else {
							System.out.println("Email already exist");
						}
						break;
						
					case 2:
						System.out.println("Enter registered email to login : ");
						String email = scanner.next();
						System.out.println("Enter registered Password to login : ");
						String password = scanner.next();
						try {
							StudentInfo StudentBean = service1.authenticateStudent(email, password);
							System.out.println("LOGGED IN SUCCESSFULLY");
							do {
								System.out.println("<-------------------------------------------->");
								System.out.println("PRESS-1 TO SEARCH BOOK BY AUTHOR");
								System.out.println("PRESS-2 TO SEARCH BOOK BY TITLE");
								System.out.println("PRESS-3 TO SEARCH BOOK BY CATEGORY");
								System.out.println("PRESS-4 TO GET INFO ABOUT ALL BOOKS");
								System.out.println("PRESS-5 TO REQUEST THE BOOK FROM LIBRARY");
								System.out.println("PRESS-6 TO RETURN THE BOOK TO LIBRARY");
								System.out.println("PRESS-7 TO RETURN BACK TO MAIN");
								System.out.println("<-------------------------------------------->");
								int choice2 = scanner.nextInt();
								switch (choice2) {
								case 1:
									System.out.println("Search book by AuthorName : ");
									String author = scanner.next();

									BooksInfo bean2 = new BooksInfo();
									bean2.setAuthor(author);
									List<BooksInfo> bookauthor = service1.searchBookByAuthor(author);
									for (BooksInfo bookBean : bookauthor) {
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available written by this author");
										}
									}
									break;
									
								case 2:
									System.out.println("Search book by BookTitle : ");
									String book_Name = scanner.next();

									BooksInfo bean3 = new BooksInfo();
									bean3.setBookName(book_Name);
									List<BooksInfo> booktitle = service1.searchBookByTitle(book_Name);
									for (BooksInfo bookBean : booktitle) {	
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available with this title");
										}
									}
									break;
									
								case 3:
									System.out.println(" Search book by BookCategory : ");
									String book_Category = scanner.next();

									BooksInfo bean4 = new BooksInfo();
									bean4.setCategory(book_Category);;
									List<BooksInfo> bookIds = service1.searchBookByCategory(book_Category);
									for (BooksInfo bookBean : bookIds) {
										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("No books are available with this Category");
										}
									}
									break;

								case 4:
									LinkedList<BooksInfo> info = service1.getBooksInfo();
									for (BooksInfo bookBean : info) {

										if (bookBean != null) {
											System.out.println("<----------------------------------->");
											System.out.println("BookId is--> "+bookBean.getBookId());
											System.out.println("BookName is--> "+bookBean.getBookName());
											System.out.println("BookAuthor is--> "+bookBean.getAuthor());
											System.out.println("BookCategory is--> "+bookBean.getCategory());
											System.out.println("BookPublisherName is--> "+bookBean.getPublishername());
										} else {
											System.out.println("Books info is null");
										}
									}
									break;
									
								case 5:
									System.out.println("Enter book Id : ");
									int bookId2 = scanner.nextInt();
									
									BooksInfo bookBean = new BooksInfo();
									bookBean.setBookId(bookId2);
									System.out.println("Enter Student Id : ");
									int StudentId = scanner.nextInt();
									
									StudentInfo Student = new StudentInfo();
									Student.setId(StudentId);;
									try {
										RequestInfo request = service1.bookRequest(Student, bookBean);
										System.out.println("Request placed to admin");
										System.out.println("<------------------------------------>");
										System.out.println("Student Id--> " + request.getStudentInfo().getId());
										System.out.println("Student name--> " + request.getStudentInfo().getName());
										System.out.println("Book Id--> " + request.getBookInfo().getBookId());
										System.out.println("Book Name--> " + request.getBookInfo().getBookName());
									} catch (Exception e) {
										System.out.println("Invalid Request");
									}
									break;
									
								case 6:
									System.out.println("Returning a book:");
									System.out.println("<------------------------------>");
									System.out.println("Enter Student Id : ");
									int Student2 = scanner.nextInt();
									System.out.println("Enter Book Id : ");
									int book = scanner.nextInt();
									
									StudentInfo StudentBean7 = new StudentInfo();
									StudentBean7.setId(Student2);
									BooksInfo bookBean7 = new BooksInfo();
									bookBean7.setBookId(book);
									try {
										RequestInfo requestInfo = service1.bookReturn(StudentBean7, bookBean7);
										System.out.println("Book is Returning to Admin");
										System.out.println("<------------------------------------->");
										System.out.println("Student Id --> "+requestInfo.getStudentInfo().getId());
										System.out.println("Book Id --> "+requestInfo.getBookInfo().getBookId());
										System.out.println("Is Returning --> "+requestInfo.isReturned());
									} catch (Exception e) {
										System.out.println("Invalid Return");
									}
									break;
									
								case 7:
									doReg();
									
								default:
									break;
								}
							} while (true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}

					case 3:
						doReg();
						break;
						
					default:
						System.out.println("Invalid Choice");
					}
				} while (true);
			}
		}while (true);
	}
}
