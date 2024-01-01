package com.travel.web.controllers.admin.review;

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
 * Servlet implementation class ReviewController
 */

public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int numOfLinesPerPage = 10;
	private ReviewDbUtils reviewDbUtils;

	@Resource(name="review/travel_ticket_booking_web")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
			super.init();

			try {
			reviewDbUtils = new ReviewDbUtils(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}

	/**
	 * MANAGE COMMAND FROM JSP
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "ADMIN";
			}
			
			switch(command) {
				case "ADMIN":
					reviewsManagement(request, response);
					break;
				case "LIST":
					reviewsList(request, response);
					break;
				case "LOCK":
					lockReview(request, response);
					break;
				case "UNLOCK":
					unlockReview(request, response);
					break;
				case "SEARCH":
					searchReview(request, response);
					break;
				
				default:
					reviewsList(request, response);

			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * SEACH FOR A REVIEW
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("search");
		
		List<Review> reviews = reviewDbUtils.searchReviews(data);
		
		// Keep track of current number of the page
		int noOfLines = reviewDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("REVIEW_LIST", reviews);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/reviewList.jsp");
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * MAKE A REVIEW AVAILABLE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void unlockReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		reviewDbUtils.unlockReview(id);
		
		reviewsList(request, response);
		
	}

	/**
	 * MAKE A REVIEW UNAVAILABLE
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void lockReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		reviewDbUtils.lockReview(id);

		reviewsList(request, response);
		
	}

	/**
	 * GET LIST OF REVIEWS BY PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void reviewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoString != null) {
			pageNo = Integer.parseInt(pageNoString);
		}
		
		int pageOffset = getPageOffset(pageNo);
		List<Review> reviews = reviewDbUtils.getReviews(numOfLinesPerPage, pageOffset);
		int noOfLines = reviewDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("REVIEW_LIST", reviews);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/reviewList.jsp");
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
	 * GO TO THE ADMIN PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void reviewsManagement(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/admin.jsp");
		dispatcher.forward(request, response);
	}
	
}
