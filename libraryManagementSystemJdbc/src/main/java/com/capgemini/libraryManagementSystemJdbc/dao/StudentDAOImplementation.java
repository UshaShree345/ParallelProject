package com.capgemini.libraryManagementSystemJdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.libraryManagementSystemJdbc.dto.BookBean;
import com.capgemini.libraryManagementSystemJdbc.dto.BookIssueDetails;
import com.capgemini.libraryManagementSystemJdbc.dto.BorrowedBooks;
import com.capgemini.libraryManagementSystemJdbc.dto.RequestDetails;
import com.capgemini.libraryManagementSystemJdbc.dto.StudentsBean;
import com.capgemini.libraryManagementSystemJdbc.exception.CommonException;

public class StudentDAOImplementation implements StudentDAO{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(StudentsBean student) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
				  PreparedStatement pstmt = conn.prepareStatement("insert into students values(?,?,?,?,?,?,?)")) {
				pstmt.setInt(1,student.getSId());
				pstmt.setString(2, student.getFirstName());
				pstmt.setString(3, student.getLastName());
				pstmt.setString(4, student.getEmail());
				pstmt.setString(5, student.getPassword());
				pstmt.setLong(6, student.getMobile());
				pstmt.setString(7, student.getRole());
				int count = pstmt.executeUpdate();
				if ((student.getEmail().isEmpty()) && (count==0)) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public StudentsBean login(String email, String password) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
				  PreparedStatement pstmt = conn.prepareStatement("select * from students where email=? and password=?");) {
				pstmt.setString(1,email);
				pstmt.setString(2,password);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					StudentsBean bean = new StudentsBean();
					bean.setSId(rs.getInt("sId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					return bean;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addBook(BookBean book) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("insert into bookbean values(?,?,?,?,?)");) {
				pstmt.setInt(1, book.getbId());
				pstmt.setString(2, book.getBookName());
				pstmt.setString(3, book.getAuthor());
				pstmt.setString(4, book.getCategory());
				pstmt.setString(5, book.getPublisher());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBook(int bId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("delete from bookbean where bid=?");) {
				pstmt.setInt(1,bId);
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBook(BookBean book) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("update bookbean set bookname=? where bid=?");) {
				pstmt.setString(1,book.getBookName());
				pstmt.setInt(2,book.getbId());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean issueBook(int bId,int sId) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from request_details where sid=? and bid=? and email=(select email from students where sid=?)")) {
				pstmt.setInt(1, sId);
				pstmt.setInt(2, bId);
				pstmt.setInt(3, sId);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					try(PreparedStatement pstmt1 = conn.prepareStatement("insert into book_issue_details values(?,?,?,?)");){
						pstmt1.setInt(1, bId);
						pstmt1.setInt(2, sId);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
						Calendar cal = Calendar.getInstance();
						String issueDate = sdf.format(cal.getTime());
						pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
						cal.add(Calendar.DAY_OF_MONTH, 7);
						String returnDate = sdf.format(cal.getTime());
						pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
						int count = pstmt1.executeUpdate();
						if(count != 0) {	
							try(PreparedStatement pstmt2 = conn.prepareStatement("Insert into borrowed_books values(?,?,(select email from students where sid=?))")){
								pstmt2.setInt(1, sId);
								pstmt2.setInt(2, bId);
								pstmt2.setInt(3, sId);
								int isBorrowed = pstmt2.executeUpdate();
								if(isBorrowed != 0) {
									return true;
								}else {
									return false;
								}
							}
						} else {
							throw new CommonException("Book Not issued");
						}					
					}
				} else {
					throw new CommonException("The respective student have not placed any request");
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean request(int sId, int bId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select count(*) as sid from borrowed_books where sid=? and bid=? and email=(select email from students where sid=?)");) {
				pstmt.setInt(1, sId);
				pstmt.setInt(2, bId);
				pstmt.setInt(3, sId);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					int isBookExists = rs.getInt("sId");
					if (isBookExists == 0) {
						try (PreparedStatement pstmt1 = conn.prepareStatement("select count(*) as sid from book_issue_details where sid=?");) {
							pstmt1.setInt(1, sId);
							rs = pstmt1.executeQuery();
							if (rs.next()) {
								int noOfBooksBorrowed = rs.getInt("sId");
								if (noOfBooksBorrowed<3) {
									try (PreparedStatement pstmt2 = conn.prepareStatement("insert into request_details values(?,(select concat(firstName,'_',lastName) from students where sid=?)"
											+ ",?,(select bookname from bookbean where bid=?),(select email from students where sid=?))");) {
										pstmt2.setInt(1,sId);
										pstmt2.setInt(2, sId);
										pstmt2.setInt(3, bId);
										pstmt2.setInt(4, bId);
										pstmt2.setInt(5, sId);
										int count = pstmt2.executeUpdate();
										if (count != 0) {
											return true;
										} else {
											return false;
										}
									}				 
								} else {
									throw new CommonException("no Of books limit has crossed");
								}
							} else {
								throw new CommonException("no of books limit has crossed");
							}		
						}				
					} else {
						throw new CommonException("You have already borrowed the requested book");
					}		
				} else {
					throw new CommonException("You have already borrowed the requested book");
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public LinkedList<BookBean> searchBookByTitle(String bookName) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where bookname=?");) {
				pstmt.setString(1,bookName);
				rs = pstmt.executeQuery();
				LinkedList<BookBean> beans = new LinkedList<BookBean>();
				while (rs.next()) {
					BookBean bean = new BookBean();
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LinkedList<BookBean> searchBookByAuthor(String author) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where author=?");) {
				pstmt.setString(1,author);
				rs = pstmt.executeQuery();
				LinkedList<BookBean> beans = new LinkedList<BookBean>();
				while (rs.next()) {
					BookBean bean = new BookBean();
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();) {
				rs = stmt.executeQuery("select * from bookbean");
				LinkedList<BookBean> beans = new LinkedList<BookBean>();
				while (rs.next()) {
					BookBean bean = new BookBean();
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int sId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select count(*) as sid from book_issue_details where sid=?");) {
				pstmt.setInt(1, sId);
				rs = pstmt.executeQuery();
				LinkedList<BookIssueDetails> beans = new LinkedList<BookIssueDetails>();
				while (rs.next()) {
					BookIssueDetails issueDetails = new BookIssueDetails();
					issueDetails.setStudentId(rs.getInt("sId"));
					beans.add(issueDetails);
				} 
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int sId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from borrowed_books where sid=?");) {
				pstmt.setInt(1, sId);
				rs = pstmt.executeQuery();
				LinkedList<BorrowedBooks> beans = new LinkedList<BorrowedBooks>();
				while (rs.next()) {
					BorrowedBooks listOfbooksBorrowed = new BorrowedBooks();
					listOfbooksBorrowed.setsId(rs.getInt("sId"));
					listOfbooksBorrowed.setbId(rs.getInt("bId"));
					listOfbooksBorrowed.setEmail(rs.getString("email"));
					beans.add(listOfbooksBorrowed);
				} 
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LinkedList<BookBean> searchBookById(int bId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where bid=?");) {
				pstmt.setInt(1, bId);
				rs=pstmt.executeQuery();
				LinkedList<BookBean> beans = new LinkedList<BookBean>();
				while (rs.next()) {
					BookBean bean = new BookBean();
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean returnBook(int bId,int sId,String status) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from book_issue_details where bid=? and sid=?");) {
				pstmt.setInt(1, bId);
				pstmt.setInt(2, sId);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					Date issueDate = rs.getDate("issueDate");
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if (daysBetween > 7) {
						float fine = daysBetween*5;
						System.out.println("The student has to pay the fine of the respective book of Rs:"+fine);
						if (status == "yes") {
							try (PreparedStatement pstmt1 = conn.prepareStatement("delete from book_issue_details where bid=? and sid=?");) {
								pstmt1.setInt(1,bId);
								pstmt1.setInt(2,sId);
								int count =  pstmt1.executeUpdate();
								if (count != 0) {
									try (PreparedStatement pstmt2 = conn.prepareStatement("delete from borrowed_books where bid=? and sid=?");) {
										pstmt2.setInt(1, bId);
										pstmt2.setInt(2, sId);
										int isReturned = pstmt2.executeUpdate();
										if (isReturned != 0 ) {
											try (PreparedStatement pstmt3 = conn.prepareStatement("delete from request_details where bid=? and sid=?");) {
												pstmt3.setInt(1, bId);
												pstmt3.setInt(2, sId);
												int isRequestDeleted = pstmt3.executeUpdate();
												if (isRequestDeleted != 0) {
													return true;
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								} else {
									return false;
								}
							}
						} else {
							throw new CommonException("The student has to pay fine for delaying book return");
						}
					} else {
						try (PreparedStatement pstmt1 = conn.prepareStatement("delete from book_issue_details where bid=? and sid=?");) {
							pstmt1.setInt(1,bId);
							pstmt1.setInt(2,sId);
							int count =  pstmt1.executeUpdate();
							if (count != 0) {
								try (PreparedStatement pstmt2 = conn.prepareStatement("delete from borrowed_books where bid=? and sid=?");) {
									pstmt2.setInt(1, bId);
									pstmt2.setInt(2, sId);
									int isReturned = pstmt2.executeUpdate();
									if (isReturned != 0 ) {
										try (PreparedStatement pstmt3 = conn.prepareStatement("delete from request_details where bid=? and sid=?");){
											pstmt3.setInt(1, bId);
											pstmt3.setInt(2, sId);
											int isRequestDeleted = pstmt3.executeUpdate();
											if (isRequestDeleted != 0) {
												return true;
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							} else {
								return false;
							}
						}
					}
				} else {
					throw new CommonException("This respective student hasn't borrowed any book");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public LinkedList<RequestDetails> showRequests() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from request_details");) {
				LinkedList<RequestDetails> beans = new LinkedList<RequestDetails>();
				while (rs.next()) {
					RequestDetails bean = new RequestDetails();
					bean.setsId(rs.getInt("sId"));
					bean.setFullName(rs.getString("fullName"));
					bean.setbId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bookName"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<BookIssueDetails> showIssuedBooks() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from book_issue_details");) {
				LinkedList<BookIssueDetails> beans = new LinkedList<BookIssueDetails>();
				while (rs.next()) {
					BookIssueDetails bean = new BookIssueDetails();
					bean.setBookId(rs.getInt("bookId"));
					bean.setStudentId(rs.getInt("studentId"));
					bean.setIssueDate(rs.getDate("issueDate"));
					bean.setReturnDate(rs.getDate("returnDate"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<StudentsBean> showStudents() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from students");) {
				LinkedList<StudentsBean> beans = new LinkedList<StudentsBean>();
				while (rs.next()) {
					StudentsBean bean = new StudentsBean();
					bean.setSId(rs.getInt("sId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pst = conn.prepareStatement("select * from students where email=? and role=?")) {
				pst.setString(1, email);
				pst.setString(2, role);
				rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement("update students set password=? where email=? and password=?");) {
						pstmt.setString(1, newPassword);
						pstmt.setString(2, email);
						pstmt.setString(3,password);
						int count=pstmt.executeUpdate();
						if (count != 0) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					throw new CommonException("student doesnt exist");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
  }
