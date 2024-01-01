package com.travel.web.controllers.admin.review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;

public class ReviewDbUtils {
private DataSource dataSource;
	
	public ReviewDbUtils(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	/**
	 * GET TOTAL NUMBER OF DATA ENTRIES
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
			String sql = "select count(*) as countLines from travel_ticket_booking_web.reviews";
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
	 * 	GET REVIEWS LIST DIVIDED BY PAGES
	 * 
	 * @param numOfLinesPerPage
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<Review> getReviews(int numOfLinesPerPage, int offset) throws Exception {
		List<Review> reviews = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.reviews order by id limit " + (offset - 1) + ", " + numOfLinesPerPage;
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get the data from the page
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String reviewer = myRs.getString("reviewer");
				int post_id = myRs.getInt("post_id");
				int rating = myRs.getInt("rating");
				Date reviewDate = myRs.getDate("reviewDate");
				String message = myRs.getString("content");
				boolean status = myRs.getBoolean("status");
				
				Review review = new Review(id, reviewer, post_id, rating, reviewDate, message, status);

				reviews.add(review);
				
			}
			return reviews;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * LOCK A REVIEW
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void lockReview(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.reviews set status = 0 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
			
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * UNLOCK A REVIEW
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void unlockReview(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.reviews set status = 1 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * SEARCH FOR REVIEWS BY DATA
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public List<Review> searchReviews(String data) throws Exception {
		List<Review> reviews = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			String sql = "";
			
			// If data == null then select of entries
			if (data != "") {
				sql = "select * from travel_ticket_booking_web.reviews where rating = " + data + ";";
			}
			else { // Else sorted by data
				sql = "select * from travel_ticket_booking_web.reviews";
			}
			
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get the value of review
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String reviewer = myRs.getString("reviewer");
				int post_id = myRs.getInt("post_id");
				int rating = myRs.getInt("rating");
				Date reviewDate = myRs.getDate("reviewDate");
				String message = myRs.getString("content");
				boolean status = myRs.getBoolean("status");
				
				Review review = new Review(id, reviewer, post_id, rating, reviewDate, message, status);

				reviews.add(review);
				
			}
			return reviews;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}
}
