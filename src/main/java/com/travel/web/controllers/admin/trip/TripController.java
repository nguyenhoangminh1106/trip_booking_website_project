package com.travel.web.controllers.admin.trip;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.user.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TripController
 */

public class TripController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int numOfLinesPerPage = 10;
	private TripDbUtils tripDbUtils;

	@Resource(name="trip/travel_ticket_booking_web")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
			super.init();

			try {
			tripDbUtils = new TripDbUtils(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}

	/**
	 * MANAGE COMMAND OF JSP
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "ADMIN";
			}
			
			switch(command) {
				case "ADMIN":
					tripsManagement(request, response);
					break;
				case "LIST":
					tripsList(request, response);
					break;
				case "ADD":
					addTrip(request, response);
					break;
				case "LOCK":
					lockTrip(request, response);
					break;
				case "UNLOCK":
					unlockTrip(request, response);
					break;
				case "SEARCH":
					searchTrip(request, response);
					break;
				case "DELETE":
					deleteTrip(request, response);
					break;
				case "UPDATE_FORM":
					updateForm(request, response);
					break;
				case "UPDATE":
					updateTrip(request, response);
					break;
				
				default:
					tripsList(request, response);

			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * SEARCH FOR A TRIP
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("search");
		
		List<Trip> trips = tripDbUtils.searchTrips(data, false);
		
		// Keep track of current page number
		int noOfLines = tripDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("TRIP_LIST", trips);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/tripList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * UPDATE THE PAGE AND ALERT
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateTrip(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		String tripName = request.getParameter("tripName");
		double price = Double.parseDouble(request.getParameter("price"));
		String img_url = request.getParameter("img_url");
		String desc_url = request.getParameter("desc_url");
		String address = request.getParameter("address");
		Boolean status = Boolean.parseBoolean(request.getParameter("status"));
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));

		Trip newTrip = new Trip(id, tripName, price, img_url, desc_url, address, startDate, endDate, status);
		tripDbUtils.updateTrip(newTrip);
		
		String isSuccess = "addSuccessfully";
		request.setAttribute("isSuccess", isSuccess);
		
		tripsList(request, response);
	}

	/**
	 * GO TO UPDATE FORM AND DISPLAY CURRENT TRIP DATA
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Trip trip = tripDbUtils.getTrip(id);
		request.setAttribute("trip", trip);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/updateTrip.jsp");
		dispatcher.forward(request, response);
		
		tripsList(request, response);
	}

	/**
	 * UNLOCK THE TRIP
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void unlockTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		tripDbUtils.unlockTrip(id);
		
		tripsList(request, response);
		
	}

	/**
	 * LOCK THE TRIP
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void lockTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		tripDbUtils.lockTrip(id);

		tripsList(request, response);
		
	}

	/**
	 * DELETE THE TRIP BY ID
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deleteTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		tripDbUtils.deleteTrip(id);
		

		tripsList(request, response);
	}

	/**
	 * ADD A NON-EXISTING TRIP
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void addTrip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tripName = request.getParameter("tripName");
		double price = Double.parseDouble(request.getParameter("price"));
		String img_url = request.getParameter("img_url");
		String desc_url = request.getParameter("desc_url");
		String address = request.getParameter("address");
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		boolean status = true;

		Trip newTrip = new Trip(tripName, price, img_url, desc_url, address, startDate, endDate, status);
		String isSuccess = tripDbUtils.addTrip(newTrip);
		
		
		request.setAttribute("isSuccess", isSuccess);
		
		tripsList(request, response);
	}

	/**
	 * GET THE TRIP LIST DIVIDED BY PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void tripsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoString != null) {
			pageNo = Integer.parseInt(pageNoString);
		}
		
		int pageOffset = getPageOffset(pageNo);
		List<Trip> trips = tripDbUtils.getTrips(numOfLinesPerPage, pageOffset);
		int noOfLines = tripDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("TRIP_LIST", trips);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/tripList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * GET TOTAL NUMBER OF PAGES
	 * 
	 * @param noOfLines
	 * @return
	 */
	public int getNoOfPages(int noOfLines) {
		return (int)Math.ceil((double)noOfLines / numOfLinesPerPage); 
	}

	/**
	 * GET THE STARTING INDEX OF THE PAGE
	 * 
	 * @param pageNo
	 * @return
	 */
	private int getPageOffset(int pageNo) {
		return (pageNo - 1) * numOfLinesPerPage + 1;
	}

	/**
	 * GO TO MANAGEMENT PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void tripsManagement(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/admin.jsp");
		dispatcher.forward(request, response);
	}
	
}
