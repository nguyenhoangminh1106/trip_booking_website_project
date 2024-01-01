package com.travel.web.controllers.user;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.booking.Booking;
import com.travel.web.controllers.admin.post.Post;
import com.travel.web.controllers.admin.review.Review;
import com.travel.web.controllers.admin.trip.Trip;
import com.travel.web.controllers.admin.user.User;

/**
 * Servlet implementation class pageController
 */
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PageDbUtils pageDbUtils;

	@Resource(name="page/travel_ticket_booking_web")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			pageDbUtils = new PageDbUtils(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * MANAGE COMMAND FROM JSP
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "HOME";
			}
			
			switch(command) {
				case "HOME":
					toHomePage(request, response);
					break;
				case "LOGIN":
					userLogin(request, response);
					break;
				case "SIGNUP":
					userSignup(request, response);
					break;
				case "SEARCH":
					searchTrips(request, response);
					break;
				case "BOOKING":
					bookingTrip(request, response);
					break;
				case "BOOKING_LIST":
					bookingList(request, response);
					break;
				case "POST":
					postList(request, response);
					break;
				case "COMMENT":
					postComment(request, response);
					break;
				case "COMMENT_LIST":
					listComments(request, response);
					break;
			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * LIST COMMENT SORT BY REVIEW_DATE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void listComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String post_id = request.getParameter("post_id");
		List<Review> reviews = pageDbUtils.getReviews(post_id);
		
		HttpSession session = request.getSession();
		session.setAttribute("post_id", post_id);
		session.setAttribute("REVIEW_LIST", reviews);
		response.sendRedirect(request.getContextPath() +"/FrontEnd/blog-single.jsp");	
		
	}

	/**
	 * POST A COMMENT TO THE POST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void postComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String post_id = request.getParameter("post_id");
		String reviewer = request.getParameter("reviewer");
		String rating = request.getParameter("rating");
		String message = request.getParameter("message");
		pageDbUtils.addComment(post_id, reviewer, rating, message);
		
		response.sendRedirect(request.getContextPath() +"/FrontEnd/tour.jsp");	
	}

	/**
	 * LIST USER'S BOOKING LIST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void bookingList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		
		List<Booking> bookingList = pageDbUtils.getBookings(user);
		session.setAttribute("BOOKING_LIST", bookingList);
		
		response.sendRedirect(request.getContextPath() +"/FrontEnd/bookingList.jsp");	
		
	}
	
	/**
	 * GET THE LIST OF THE POST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void postList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		
		List<Post> postList = pageDbUtils.getPosts();
		session.setAttribute("POST_LIST", postList);
		response.sendRedirect(request.getContextPath() +"/FrontEnd/blog.jsp");	
		
	}

	/**
	 * ALLOW USER BOOKING A TRIP THAT AVAILABLE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void bookingTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String tripName = request.getParameter("tripName");
		double price = Double.parseDouble(request.getParameter("price"));
		int noOfAdults = Integer.parseInt(request.getParameter("noOfAdults"));
		int noOfChildren = Integer.parseInt(request.getParameter("noOfChildren"));
		String booker = request.getParameter("booker");
		
		 
	    Date bookingDate = new Date(System.currentTimeMillis());
	    
	    // Get the real price users have to pay
	    double totalPrice = (price / 4 * noOfAdults) + (price / 8 * noOfChildren);
		
		Booking newBooking = new Booking(id, tripName, totalPrice, noOfAdults, noOfChildren, booker, bookingDate, true);
		
		pageDbUtils.addBooking(newBooking);
		
		HttpSession session = request.getSession();
		session.setAttribute("BookingSuccessful", true);
			
		response.sendRedirect(request.getContextPath() +"/FrontEnd/tour.jsp");	
	}

	/**
	 * SEARCH FOR TRIPS BY START DATE AND PRICE OR TRIP NAME
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchTrips(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tripName = request.getParameter("tripName");
		String dateFrom = request.getParameter("dateFrom");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		List<Trip> trips = pageDbUtils.searchTrips(tripName, dateFrom, minPrice, maxPrice);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("SEARCH_TRIP_LIST", trips);
		response.sendRedirect(request.getContextPath() + "/FrontEnd/tour.jsp");
		
	}

	/**
	 * ALLOW USER TO SIGNUP AND ADD TO DATABASE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void userSignup(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("account");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String role = "User";
		boolean status = true;
		int role_id = getRole_id(role);

		User newUser = new User(userName, password, fullName, email, phoneNumber, address, status, role_id);
		String isSuccess = pageDbUtils.addUser(newUser);
		
		HttpSession session = request.getSession();
		session.setAttribute("isSuccess", isSuccess);
		response.sendRedirect(request.getContextPath() + "/FrontEnd/login.jsp");
	}

	/**
	 * GET TO INT_ROLE_ID FORM STRING_ROLE_ID
	 * 
	 * @param stringRole
	 * @return
	 */
	public int getRole_id(String stringRole) {
		if (stringRole.equals("Admin")) {
			return 1;
		}
		else if (stringRole.equals("User")) {
			return 0;
		}

		return -1;
	}

	/**
	 * CHECK FOR LOG_IN VALIDATION
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = pageDbUtils.checkLogin(email, password);
		List<Trip> trips = getTrips(request, response, "price");
		List<Trip> tripsActive = getTripsActive(request, response, "price");
		
		
		HttpSession session = request.getSession();
		session.setAttribute("TRIP_LIST", trips);
		session.setAttribute("TRIP_LIST_ACTIVE", tripsActive);
		
		if (user != null) {
			session.setAttribute("user", user);
			session.setAttribute("isLogin", true);
			
			response.sendRedirect(request.getContextPath() +"/FrontEnd/index.jsp");
		}
		else {
			String loginFail = "loginFail";
			
			session.setAttribute("loginFail", loginFail);
			loginFail = "";
			response.sendRedirect(request.getContextPath() +"/FrontEnd/login.jsp");
		}	
	}
	
	/**
	 * REDIRECT TO HOME PAGE
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void toHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Trip> trips = getTrips(request, response, "price");
		List<Trip> tripsActive = getTripsActive(request, response, "price");
		HttpSession session = request.getSession();
		session.setAttribute("TRIP_LIST", trips);
		session.setAttribute("TRIP_LIST_ACTIVE", tripsActive);
		response.sendRedirect(request.getContextPath() +"/FrontEnd/index.jsp");
	}

	/**
	 * GET THE AVAILABLE TRIP SORT BY DATA
	 * 
	 * @param request
	 * @param response
	 * @param sortedBy
	 * @return
	 * @throws Exception
	 */
	private List<Trip> getTripsActive(HttpServletRequest request, HttpServletResponse response, String sortedBy) throws Exception {
		// TODO Auto-generated method stub
		return pageDbUtils.getTripsActive(sortedBy);
	}

	/**
	 * GET ALL THE TRIPS
	 * 
	 * @param request
	 * @param response
	 * @param sortedBy
	 * @return
	 * @throws Exception
	 */
	private List<Trip> getTrips(HttpServletRequest request, HttpServletResponse response, String sortedBy) throws Exception{
		return pageDbUtils.getTrips(sortedBy);		
	}

}
