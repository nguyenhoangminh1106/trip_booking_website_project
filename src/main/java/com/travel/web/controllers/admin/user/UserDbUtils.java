package com.travel.web.controllers.admin.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDbUtils {
	private DataSource dataSource;
	
	
	public UserDbUtils(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}


	/**
	 * ADD USER
	 * @param newUser
	 * @return
	 * @throws Exception
	 */
	public String addUser(User newUser) throws Exception {
		Connection myConn = null;
		Statement myCheckStmt = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			//mySQL command
			String checkSql = "select count(*) as count from travel_ticket_booking_web.users "
					+ "where email = '" + newUser.getEmail() + "';";
			
			myCheckStmt = myConn.createStatement();
			myRs = myCheckStmt.executeQuery(checkSql);
			
			myRs.next();
			int countEmail = myRs.getInt("count");
			
			if (countEmail == 0) {
				String sql = "insert into travel_ticket_booking_web.users "
						+ "(userName, password, fullName, email, phoneNumber, address, status, role_id) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, newUser.getUserName());
				myStmt.setString(2, newUser.getPassword());
				myStmt.setString(3, newUser.getFullName());
				myStmt.setString(4, newUser.getEmail());
				myStmt.setString(5, newUser.getPhoneNumber());
				myStmt.setString(6, newUser.getAddress());
				myStmt.setBoolean(7, newUser.isStatus());
				myStmt.setInt(8, newUser.getRole_id());
			
				myStmt.execute();
				return "addSuccessfully";
			}
			else {
				return "dataExists";
			}
			
		}
		finally {
			close(myConn, myStmt, myRs);
			close(null, myCheckStmt, null);
		}
		
	}


	/**
	 * CLOSE DATABASE CONNECTION
	 * 
	 * @param myConn
	 * @param myStmt
	 * @param myRs
	 */
	private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
		try {
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myConn.close();
			}
			if (myRs != null) {
				myConn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * CLOSE DATABASE CONNECTION
	 * 
	 * @param myConn
	 * @param myStmt
	 * @param myRs
	 */
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myConn.close();
			}
			if (myRs != null) {
				myConn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * GET LIST OF USERS DIVIDED BY PAGE
	 * 
	 * @param numOfLinesPerPage
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<User> getUsers(int numOfLinesPerPage, int offset) throws Exception {
		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.users order by id limit " + (offset - 1) + ", " + numOfLinesPerPage;
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String userName = myRs.getString("userName");
				String password = myRs.getString("password");
				String fullName = myRs.getString("fullName");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phoneNumber");
				String address = myRs.getString("address");
				boolean status = myRs.getBoolean("status");
				int role_id = myRs.getInt("role_id");
				
				User user = new User(id, userName, password, fullName, email, phoneNumber, address, status, role_id);

				users.add(user);
				
			}
			return users;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}
	
	/**
	 * GET TOTAL TABLE ENTRIES
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getNoOfLines() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select count(*) as countLines from travel_ticket_booking_web.users";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			myRs.next();
			int numOfLines = myRs.getInt("countLines");
			return numOfLines;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}



	/**
	 * SEARCH FOR USERS BY DATA
	 * 
	 * @param data
	 * @param isNumeric
	 * @return
	 * @throws Exception
	 */
	public List<User> searchUsers(String data, boolean isNumeric) throws Exception {
		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "";
			
			if (isNumeric) {
				sql = "select * from travel_ticket_booking_web.users where phoneNumber like '" + data + "%';";
			}
			else {
				sql = "select * from travel_ticket_booking_web.users where email like '" + data + "%';";
			}
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String userName = myRs.getString("userName");
				String password = myRs.getString("password");
				String fullName = myRs.getString("fullName");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phoneNumber");
				String address = myRs.getString("address");
				boolean status = myRs.getBoolean("status");
				int role_id = myRs.getInt("role_id");
				
				User user = new User(id, userName, password, fullName, email, phoneNumber, address, status, role_id);

				users.add(user);
				
			}
			return users;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}



	/**
	 * LOCK A USER
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void lockUser(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "update travel_ticket_booking_web.users set status = 0 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
			
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * UNLOCK A USER
	 * @param id
	 * @throws Exception
	 */
	public void unlockUser(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "update travel_ticket_booking_web.users set status = 1 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}


	/**
	 * DELETE A USER
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "delete from travel_ticket_booking_web.users where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}



	public User getUser(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.users where id = " + id + ";";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			myRs.next();
			
			int int_id = myRs.getInt("id");
			String userName = myRs.getString("userName");
			String password = myRs.getString("password");
			String fullName = myRs.getString("fullName");
			String email = myRs.getString("email");
			String phoneNumber = myRs.getString("phoneNumber");
			String address = myRs.getString("address");
			boolean status = myRs.getBoolean("status");
			int role_id = myRs.getInt("role_id");
			
			User user = new User(int_id, userName, password, fullName, email, phoneNumber, address, status, role_id);

			return user;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}



	/**
	 * UPDATE A USER INFO
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "update travel_ticket_booking_web.users set "
						+ "userName = ?, "
						+ "password = ?, "
						+ "fullName = ?, "
						+ "email = ?, "
						+ "phoneNumber = ?, "
						+ "address = ?, "
						+ "status = ?, "
						+ "role_id = ? "
						+ "where id = " + user.getId() + ";";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, user.getUserName());
			myStmt.setString(2, user.getPassword());
			myStmt.setString(3, user.getFullName());
			myStmt.setString(4, user.getEmail());
			myStmt.setString(5, user.getPhoneNumber());
			myStmt.setString(6, user.getAddress());
			myStmt.setBoolean(7, user.isStatus());
			myStmt.setInt(8, user.getRole_id());
			
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
}
