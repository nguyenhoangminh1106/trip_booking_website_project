package com.travel.web.controllers.admin.post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;

public class PostDbUtils {
	private DataSource dataSource;
	
	public PostDbUtils(DataSource dataSource) {
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
			myConn = dataSource.getConnection();
			String sql = "select count(*) as countLines from travel_ticket_booking_web.posts";
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
	 * GET LIST OF THE POSTS DIVIDED BY PAGE
	 * 
	 * @param numOfLinesPerPage
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<Post> getPosts(int numOfLinesPerPage, int offset) throws Exception {
		List<Post> posts = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.posts order by id limit " + (offset - 1) + ", " + numOfLinesPerPage;
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get the value of the post
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
	 * ADD A POST TO THE DATABASE
	 * 
	 * @param newPost
	 * @return
	 * @throws Exception
	 */
	public String addPost(Post newPost) throws Exception {
		Connection myConn = null;
		Statement myCheckStmt = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			// Create connections
			myConn = dataSource.getConnection();
			
			// mySQL command sorted by postName
			String checkSql = "select count(*) as count from travel_ticket_booking_web.posts "
					+ "where postName = '" + newPost.getPostName() + "';";
			
			myCheckStmt = myConn.createStatement();
			myRs = myCheckStmt.executeQuery(checkSql);
			
			myRs.next();
			int countPost = myRs.getInt("count");
			
			// If post not exists then add post
			if (countPost == 0) {
				myConn = dataSource.getConnection();
				String sql = "insert into travel_ticket_booking_web.posts "
							+ "(postName, img_url, desc_url) "
							+ "values (?, ?, ?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, newPost.getPostName());
				myStmt.setString(2, newPost.getImg_url());
				myStmt.setString(3, newPost.getDesc_url());
				
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
	 * SEARCH FOR A POST
	 * 
	 * @param data
	 * @param isNumeric
	 * @return
	 * @throws Exception
	 */
	public List<Post> searchPosts(String data, boolean isNumeric) throws Exception {
		List<Post> posts = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			String sql = "";
			
			if(!isNumeric) {
				// mySQL command sorted by data
				sql = "select * from travel_ticket_booking_web.posts where postName like '%" + data + "%';";
			}
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			// Get the value of post found
			while(myRs.next())  {
				int int_id = myRs.getInt("id");
				String postName = myRs.getString("postName");
				String img_url = myRs.getString("img_url");
				String desc_url = myRs.getString("desc_url");
				
				Post post = new Post(int_id, postName, img_url, desc_url);

				posts.add(post);
				
			}
			return posts;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	/**
	 * DELETE A POST
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deletePost(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "delete from travel_ticket_booking_web.posts where id = " + id + ";";
			myStmt = myConn.createStatement();
			myStmt.execute(sql);
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	/**
	 * 	GET THE POST WITH GIVEN ID
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Post getPost(String id) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "select * from travel_ticket_booking_web.posts where id = " + id + ";";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			myRs.next();
			
			int int_id = myRs.getInt("id");
			String postName = myRs.getString("postName");
			String img_url = myRs.getString("img_url");
			String desc_url = myRs.getString("desc_url");
			
			Post post = new Post(int_id, postName, img_url, desc_url);

			return post;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * UPDATE THE POST
	 * 
	 * @param post
	 * @throws Exception
	 */
	public void updatePost(Post post) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// Create connection
			myConn = dataSource.getConnection();
			
			// mySQL command
			String sql = "update travel_ticket_booking_web.posts set "
						+ "postName = ?, "
						+ "img_url = ?, "
						+ "desc_url = ? "
						+ "where id = " + post.getId() + ";";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, post.getPostName());
			myStmt.setString(2, post.getImg_url());
			myStmt.setString(3, post.getDesc_url());
			
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
}
