package com.travel.web.controllers.admin.booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;

public class BookingDbUtils {
	private DataSource dataSource;
	
	public BookingDbUtils(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	/**
	 * GET TOTAL NUMBER OF ENTRIES IN THE TABLE
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getNoOfLines() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select count(*) as countLines from travel_ticket_booking_web.bookings";
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
	 * 	CLOSE THE CONNECTION WITH DATABASE
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
	 * CLOSE THE CONNECTION WITH DATABASE
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
	 * GET LIST OF ENTRIES DIVIDED BY PAGE
	 * 
	 * @param numOfLinesPerPage
	 * @param offset
	 * @return	List	List of entries in page x
	 * @throws Exception
	 */
	public List<Booking> getBookings(int numOfLinesPerPage, int offset) throws Exception {
		List<Booking> bookings = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.bookings order by id limit " + (offset - 1) + ", " + numOfLinesPerPage;
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get the value for booking data
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String tripName = myRs.getString("tripName");
				double price = myRs.getDouble("price");
				int noOfAdults = myRs.getInt("noOfAdults");
				int noOfChildren = myRs.getInt("noOfChildren");
				String booker = myRs.getString("booker");
				Date bookingDate = myRs.getDate("bookingDate");
				boolean status = myRs.getBoolean("status");
				
				Booking booking = new Booking(id, tripName, price, noOfAdults, noOfChildren, booker, bookingDate, status);

				bookings.add(booking);
				
			}
			return bookings;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * 	MAKE BOOKING UNAVAILABLE
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void lockBooking(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.bookings set status = 0 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
			
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	
	/**
	 * MAKE BOOKING AVAILABLE
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void unlockBooking(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Make connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.bookings set status = 1 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * GET THE LIST OF BOOKINGS WITH CONDITIONS
	 * 
	 * @param data
	 * @param isNumeric
	 * @return
	 * @throws Exception
	 */
	public List<Booking> searchBookings(String data, boolean isNumeric) throws Exception {
		List<Booking> bookings = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connetion
			myConn = dataSource.getConnection();
			String sql = "";
			
			if(!isNumeric) {
				// mySQL command to get list of booking containing data
				sql = "select * from travel_ticket_booking_web.bookings where booker like '%" + data + "%';";
			}
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get value of each data entries found
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String tripName = myRs.getString("tripName");
				double price = myRs.getDouble("price");
				int noOfAdults = myRs.getInt("noOfAdults");
				int noOfChildren = myRs.getInt("noOfChildren");
				String booker = myRs.getString("booker");
				Date bookingDate = myRs.getDate("bookingDate");
				boolean status = myRs.getBoolean("status");
				
				Booking booking = new Booking(id, tripName, price, noOfAdults, noOfChildren, booker, bookingDate, status);

				bookings.add(booking);
				
			}
			return bookings;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}
}
