package com.travel.web.controllers.admin.booking;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;
import com.travel.web.controllers.admin.trip.TripDbUtils;

/**
 * Servlet implementation class BookingController
 */
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int numOfLinesPerPage = 10;
	private BookingDbUtils bookingDbUtils;

	@Resource(name="booking/travel_ticket_booking_web")
	private DataSource dataSource;

	// Initialize the dsUtils
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
			super.init();

			try {
			bookingDbUtils = new BookingDbUtils(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}
	
	// Get the command from JPS
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "ADMIN";
			}
			
			switch(command) {
				case "ADMIN":
					bookingsManagement(request, response);
					break;
				case "LIST":
					bookingsList(request, response);
					break;
				case "LOCK":
					lockBooking(request, response);
					break;
				case "UNLOCK":
					unlockBooking(request, response);
					break;
				case "SEARCH":
					searchBooking(request, response);
					break;
				
				default:
					bookingsList(request, response);

			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	
	/**
	 * SEARCH FOR BOOKINGS AND SEND TO JSP LIST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("search");
		
		List<Booking> bookings = bookingDbUtils.searchBookings(data, false);
		
		int noOfLines = bookingDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("BOOKING_LIST", bookings);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/bookingList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * UNLOCK A BOOKING AND SEND TO JSP LIST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void unlockBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		bookingDbUtils.unlockBooking(id);
		
		bookingsList(request, response);
		
	}

	/**
	 * LOCK A BOOKING AND SEND TO JSP LIST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void lockBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		bookingDbUtils.lockBooking(id);

		bookingsList(request, response);
		
	}

	/**
	 * GET THE BOOKING LIST AND SEND TO JSP LIST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void bookingsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoString != null) {
			pageNo = Integer.parseInt(pageNoString);
		}
		
		// Keep track of current page number
		int pageOffset = getPageOffset(pageNo);
		List<Booking> bookings = bookingDbUtils.getBookings(numOfLinesPerPage, pageOffset);
		int noOfLines = bookingDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("BOOKING_LIST", bookings);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/bookingList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * GET NUMBER OF THE PAGE
	 * 
	 * @param noOfLines		Total entries of the data
	 * @return int			Number of the page
	 */
	public int getNoOfPages(int noOfLines) {
		return (int)Math.ceil((double)noOfLines / numOfLinesPerPage); 
	}

	/**
	 * GET THE STARTING ENTRY INDEX OF EACH PAGE
	 * 
	 * @param pageNo	Input number of page
	 * @return	int		Index of the starting entry
	 */
	private int getPageOffset(int pageNo) {
		return (pageNo - 1) * numOfLinesPerPage + 1;
	}

	/**
	 * GO TO THE ADMIN PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void bookingsManagement(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/admin.jsp");
		dispatcher.forward(request, response);
	}

}
