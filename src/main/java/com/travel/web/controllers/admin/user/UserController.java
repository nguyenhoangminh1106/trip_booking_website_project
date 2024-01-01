package com.travel.web.controllers.admin.user;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int numOfLinesPerPage = 10;
	private UserDbUtils userDbUtils;

	@Resource(name="user/travel_ticket_booking_web")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
			super.init();

			try {
			userDbUtils = new UserDbUtils(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}

	/**
	 * MANGE THE COMMAND FROM JSP
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "ADMIN";
			}
			switch(command) {
			case "ADMIN":
				usersManagement(request, response);
				break;
			case "LIST":
				usersList(request, response);
				break;
			case "ADD":
				addUser(request, response);
				break;
			case "SEARCH":
				searchUser(request, response);
				break;
			case "LOCK":
				lockUser(request, response);
				break;
			case "UNLOCK":
				unlockUser(request, response);
				break;
			case "DELETE":
				deleteUser(request, response);
				break;
			case "UPDATE_FORM":
				updateForm(request, response);
				break;
			case "UPDATE":
				updateUser(request, response);
				break;
				
			default:
				usersList(request, response);

			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * UPDATE USER
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("account");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		Boolean status = Boolean.parseBoolean(request.getParameter("status"));
		String role = request.getParameter("role");
		int role_id = getRole_id(role);

		User newUser = new User(id, userName, password, fullName, email, phoneNumber, address, status, role_id);
		userDbUtils.updateUser(newUser);
		
		String isSuccess = "addSuccessfully";
		request.setAttribute("isSuccess", isSuccess);
		
		usersList(request, response);
	}

	/**
	 * GO TO UPDATE FORM
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		User user = userDbUtils.getUser(id);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/updateUser.jsp");
		dispatcher.forward(request, response);
		
		usersList(request, response);
	}

	/**
	 * DELETE A USER 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		userDbUtils.deleteUser(id);
		

		usersList(request, response);
	}

	/**
	 * UNLOCK A USER
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void unlockUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		userDbUtils.unlockUser(id);
		
		usersList(request, response);
		
	}

	/**
	 * LOCK A USER
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void lockUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		userDbUtils.lockUser(id);

		usersList(request, response);
		
	}

	/**
	 * SEARCH FOR USERS BY NAME OR EMAIL
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("search");
		
		List<User> users = userDbUtils.searchUsers(data, isNumeric(data));
		
		int noOfLines = userDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("USER_LIST", users);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/userList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * ADD USER
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("account");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String role = request.getParameter("role");
		boolean status = true;
		int role_id = getRole_id(role);

		User newUser = new User(userName, password, fullName, email, phoneNumber, address, status, role_id);
		
		String isSuccess = userDbUtils.addUser(newUser);
		request.setAttribute("isSuccess", isSuccess);
		
		usersList(request, response);
	}

	/**
	 * GO TO MANAGEMENT PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void usersManagement(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/admin.jsp");
		dispatcher.forward(request, response);
	}

	private void usersList(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoString != null) {
			pageNo = Integer.parseInt(pageNoString);
		}
		
		int pageOffset = getPageOffset(pageNo);
		List<User> users = userDbUtils.getUsers(numOfLinesPerPage, pageOffset);
		int noOfLines = userDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("USER_LIST", users);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/userList.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * CONVERT THE STRING_ROLE TO INT ROLE
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
	 * GET THE STARTING INDEX OF THE PAGE
	 * 
	 * @param pageNo
	 * @return
	 */
	public int getPageOffset(int pageNo) {
		return (pageNo - 1) * numOfLinesPerPage + 1;
	}
	
	/**
	 * GET THE TOTAL NUMBER OF PAGES
	 * 
	 * @param noOfLines
	 * @return
	 */
	public int getNoOfPages(int noOfLines) {
		return (int)Math.ceil((double)noOfLines / numOfLinesPerPage); 
	}
	
	/**
	 * CHECK WHETHER A STRING IS NUMERIC
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }
}