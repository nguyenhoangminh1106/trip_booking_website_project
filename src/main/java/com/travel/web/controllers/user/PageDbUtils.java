package com.travel.web.controllers.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.booking.Booking;
import com.travel.web.controllers.admin.post.Post;
import com.travel.web.controllers.admin.review.Review;
import com.travel.web.controllers.admin.trip.Trip;
import com.travel.web.controllers.admin.user.User;
import com.travel.web.controllers.admin.user.UserDbUtils;

public class PageDbUtils {
	private DataSource dataSource;
	
	public PageDbUtils(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
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
	 * CHECK USER'S LOGIN INFO
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User checkLogin(String email, String password) throws Exception{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.users "
						+ "where email = '" + email + "' and password = '" + password + "';";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				int int_id = myRs.getInt("id");
				String userName = myRs.getString("userName");
				String fullName = myRs.getString("fullName");
				String phoneNumber = myRs.getString("phoneNumber");
				String address = myRs.getString("address");
				boolean status = myRs.getBoolean("status");
				int role_id = myRs.getInt("role_id");
				
				User user = new User(int_id, userName, password, fullName, email, phoneNumber, address, status, role_id);

				if (user.isStatus()) {
					return user;
				}
			}
			return null;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * ADD A USER WHEN SIGNING UP
	 * 
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
			myConn = dataSource.getConnection();
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
	 * GET THE LIST OF TRIPS SORTED BY DATA
	 * @param sortedBy
	 * @return
	 * @throws Exception
	 */
	public List<Trip> getTrips(String sortedBy) throws Exception {
		List<Trip> trips = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.trips where status = 1 order by " + sortedBy +  ";";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String tripName = myRs.getString("tripName");
				double price = myRs.getDouble("price");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				String address = myRs.getString("address");
				Date startDate = myRs.getDate("startDate");
				Date endDate = myRs.getDate("endDate");
				boolean status = myRs.getBoolean("status");
				
				Trip trip = new Trip(id, tripName, price, img_url, desc_url, address, startDate, endDate, status);

				trips.add(trip);
				
			}
			return trips;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}
	
	/**
	 * GET THE CURRENT ACTIVE TRIP SORTED BY DATA
	 * 
	 * @param sortedBy
	 * @return
	 * @throws Exception
	 */
	public List<Trip> getTripsActive(String sortedBy) throws Exception {
		List<Trip> trips = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.trips where endDate > curdate() and status = 1 order by " + sortedBy +  ";";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String tripName = myRs.getString("tripName");
				double price = myRs.getDouble("price");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				String address = myRs.getString("address");
				Date startDate = myRs.getDate("startDate");
				Date endDate = myRs.getDate("endDate");
				boolean status = myRs.getBoolean("status");
				
				Trip trip = new Trip(id, tripName, price, img_url, desc_url, address, startDate, endDate, status);

				trips.add(trip);
				
			}
			return trips;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * SEARCH FOR TRIPS BY DATES, NAMES AND PRICES
	 * 
	 * @param tripInputName
	 * @param dateFrom
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 * @throws Exception
	 */
	public List<Trip> searchTrips(String tripInputName, String dateFrom, String minPrice, String maxPrice) throws Exception {
		List<Trip> trips = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		int intMinPrice = Integer.parseInt(minPrice) / 1000;
		int intMaxPrice = Integer.parseInt(maxPrice) / 1000;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "";
			if (tripInputName != "") {
				 sql = "select * from travel_ticket_booking_web.trips where startDate >= " + dateFrom
						+ " and " + intMinPrice + " <= price"
						+ " and tripName = '" + tripInputName + "'"
						+ " and price <= " + intMaxPrice + " and status = 1 order by price;";
			}
			else {
				 sql = "select * from travel_ticket_booking_web.trips where startDate >= " + dateFrom
						+ " and " + intMinPrice + " <= price"
						+ " and price <= " + intMaxPrice + " and status = 1 order by price;";
			}
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				double price = myRs.getDouble("price");
				String tripName = myRs.getString("tripName");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				String address = myRs.getString("address");
				Date startDate = myRs.getDate("startDate");
				Date endDate = myRs.getDate("endDate");
				boolean status = myRs.getBoolean("status");
				
				Trip trip = new Trip(id, tripName, price, img_url, desc_url, address, startDate, endDate, status);

				trips.add(trip);
				
			}
			return trips;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * ADD A BOOKING FROM USER
	 * 
	 * @param newBooking
	 * @return
	 * @throws Exception
	 */
	public String addBooking(Booking newBooking) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
				String sql = "insert into travel_ticket_booking_web.bookings "
							+ "(tripName, price, noOfAdults, noOfChildren, booker, bookingDate, status) "
							+ "values (?, ?, ?, ?, ?, ?, ?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, newBooking.getTripName());
				myStmt.setDouble(2, newBooking.getPrice());
				myStmt.setInt(3, newBooking.getNoOfAdults());
				myStmt.setInt(4, newBooking.getNoOfChildren());
				myStmt.setString(5, newBooking.getBooker());
				myStmt.setDate(6, newBooking.getBookingDate());
				myStmt.setBoolean(7, newBooking.isStatus());
				
				myStmt.execute();
				return "addSuccessfully";
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * LIST BOOKING FOR A USER
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Booking> getBookings(User user) throws Exception {
		List<Booking> bookings = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.bookings where booker = '" + user.getUserName() + "' order by bookingDate;";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
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
	 * GET CURRENT POST AVAILABLE
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Post> getPosts() throws Exception {
		List<Post> posts = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.posts order by id;";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String postName = myRs.getString("postName");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				
				Post post = new Post(id, postName, img_url, desc_url);

				posts.add(post);
				
			}
			return posts;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * ADD A COMMENT THAT IS ALLOWED
	 * 
	 * @param post_id
	 * @param reviewer
	 * @param rating
	 * @param message
	 * @throws Exception
	 */
	public void addComment(String post_id, String reviewer, String rating, String message) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
				String sql = "insert into travel_ticket_booking_web.reviews "
							+ "(reviewer, post_id, rating, reviewDate, status, content) "
							+ "values (?, ?, ?, curDate(), ?, ?)";
				
				
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, reviewer);
				myStmt.setString(2, post_id);
				myStmt.setString(3, rating);
				myStmt.setBoolean(4, true);
				myStmt.setString(5, message);
				
				myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * GET REVIEWS LIST FOR EACH POST
	 * 
	 * @param post_id
	 * @return
	 * @throws Exception
	 */
	public List<Review> getReviews(String post_id) throws Exception {
		List<Review> reviews = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from travel_ticket_booking_web.reviews where post_id = " + post_id + " order by reviewDate;";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int id = myRs.getInt("id");
				String reviewer = myRs.getString("reviewer");
				int int_postId = Integer.parseInt(post_id);
				int rating = myRs.getInt("rating");
				Date reviewDate = myRs.getDate("reviewDate");
				String message = myRs.getString("content");
				boolean status = myRs.getBoolean("status");
				
				if (status) {
					Review review = new Review(id, reviewer, int_postId, rating, reviewDate, message, status);

					reviews.add(review);
				}
				
			}
			return reviews;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
}
