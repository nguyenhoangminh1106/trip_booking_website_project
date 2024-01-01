package com.travel.web.controllers.admin.trip;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;
import com.travel.web.controllers.admin.user.User;

public class TripDbUtils {
	private DataSource dataSource;
	
	public TripDbUtils(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	/**
	 * GET TOTAL LINE NUMBERS
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
			String sql = "select count(*) as countLines from travel_ticket_booking_web.trips";
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
	 * GET THE TRIP LIST DIVIDED BY PAGE
	 * 
	 * @param numOfLinesPerPage
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<Trip> getTrips(int numOfLinesPerPage, int offset) throws Exception {
		List<Trip> trips = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.trips order by id limit " + (offset - 1) + ", " + numOfLinesPerPage;
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
	 * ADD A TRIP TO DATABASE
	 * 
	 * @param newTrip
	 * @return
	 * @throws Exception
	 */
	public String addTrip(Trip newTrip) throws Exception {
		Connection myConn = null;
		Statement myCheckStmt = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			//Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String checkSql = "select count(*) as count from travel_ticket_booking_web.trips "
					+ "where tripName = '" + newTrip.getTripName() + "';";
			
			myCheckStmt = myConn.createStatement();
			myRs = myCheckStmt.executeQuery(checkSql);
			
			myRs.next();
			int countTrip = myRs.getInt("count");
			
			
			if (countTrip == 0) {
				myConn = dataSource.getConnection();
				String sql = "insert into travel_ticket_booking_web.trips "
							+ "(tripName, price, img_url, desc_url, address, startDate, endDate, status) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, newTrip.getTripName());
				myStmt.setDouble(2, newTrip.getPrice());
				myStmt.setString(3, newTrip.getImg_url());
				myStmt.setString(4, newTrip.getDesc_url());
				myStmt.setString(5, newTrip.getAddress());
				myStmt.setDate(6, newTrip.getStartDate());
				myStmt.setDate(7, newTrip.getEndDate());
				myStmt.setBoolean(8, newTrip.isStatus());
				
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
	 * 	DELETE A TRIP
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteTrip(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create a connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "delete from travel_ticket_booking_web.trips where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * LOCK A TRIP
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void lockTrip(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			//mySQL command
			String sql = "update travel_ticket_booking_web.trips set status = 0 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
			
			
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	
	/**
	 * UNLOCK A TRIP
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void unlockTrip(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.trips set status = 1 where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * UPDATE A TRIP
	 * 
	 * @param trip
	 * @throws Exception
	 */
	public void updateTrip(Trip trip) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.trips set "
						+ "tripName = ?, "
						+ "price = ?, "
						+ "img_url = ?, "
						+ "desc_url = ?, "
						+ "address = ?, "
						+ "startDate = ?, "
						+ "endDate = ?, "
						+ "status = ? "
						+ "where id = " + trip.getId() + ";";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, trip.getTripName());
			myStmt.setDouble(2, trip.getPrice());
			myStmt.setString(3, trip.getImg_url());
			myStmt.setString(4, trip.getDesc_url());
			myStmt.setString(5, trip.getAddress());
			myStmt.setDate(6, trip.getStartDate());
			myStmt.setDate(7, trip.getEndDate());
			myStmt.setBoolean(8, trip.isStatus());
			
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	/**
	 * GET A TRIP BY ID
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Trip getTrip(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.trips where id = " + id + ";";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			myRs.next();
			
			int int_id = myRs.getInt("id");
			String tripName = myRs.getString("tripName");
			double price = myRs.getDouble("price");
			String img_url = myRs.getString("img_url");
			String desc_url = myRs.getString("desc_url");
			String address = myRs.getString("address");
			Date startDate = myRs.getDate("startDate");
			Date endDate = myRs.getDate("endDate");
			boolean status = myRs.getBoolean("status");
			
			Trip trip = new Trip(int_id, tripName, price, img_url, desc_url, address, startDate, endDate, status);

			return trip;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}



	/**
	 * SEARCH FOR A TRIP BY DATA
	 * 
	 * @param data
	 * @param isNumeric
	 * @return
	 * @throws Exception
	 */
	public List<Trip> searchTrips(String data, boolean isNumeric) throws Exception {
		List<Trip> trips = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "";
			
			if(!isNumeric) {
				sql = "select * from travel_ticket_booking_web.trips where tripName like '%" + data + "%';";
			}
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())  {
				int int_id = myRs.getInt("id");
				String tripName = myRs.getString("tripName");
				double price = myRs.getDouble("price");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				String address = myRs.getString("address");
				Date startDate = myRs.getDate("startDate");
				Date endDate = myRs.getDate("endDate");
				boolean status = myRs.getBoolean("status");
				
				Trip trip = new Trip(int_id, tripName, price, img_url, desc_url, address, startDate, endDate, status);

				trips.add(trip);
				
			}
			return trips;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}
}
