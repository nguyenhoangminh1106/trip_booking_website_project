package com.travel.web.controllers.admin.post;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.travel.web.controllers.admin.trip.Trip;

/**
 * Servlet implementation class PostController
 */
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int numOfLinesPerPage = 10;
	private PostDbUtils postDbUtils;

	@Resource(name="post/travel_ticket_booking_web")
	private DataSource dataSource;

	// Initialize the database utils
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
			super.init();

			try {
			postDbUtils = new PostDbUtils(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}
	
	/**
	 * MANAGE THE COMMAND FROM JSP PAGE
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if (command == null) {
				command = "ADMIN";
			}
			
			switch(command) {
				case "ADMIN":
					postsManagement(request, response);
					break;
				case "LIST":
					postsList(request, response);
					break;
				case "ADD":
					addPost(request, response);
					break;
				case "SEARCH":
					searchPost(request, response);
					break;
				case "DELETE":
					deletePost(request, response);
					break;
				case "UPDATE_FORM":
					updateForm(request, response);
					break;
				case "UPDATE":
					updatePost(request, response);
					break;
				
				default:
					postsList(request, response);

			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * GET DATA AND UPDATE A POST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updatePost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		String postName = request.getParameter("postName");
		String img_url = request.getParameter("img_url");
		String desc_url = request.getParameter("desc_url");

		Post newPost = new Post(id, postName, img_url, desc_url);
		postDbUtils.updatePost(newPost);
		
		String isSuccess = "addSuccessfully";
		request.setAttribute("isSuccess", isSuccess);
		
		postsList(request, response);
	}

	/**
	 * GO TO UPDATE FORM AND DISPLAY THE POST CURRENT DATA
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Post post = postDbUtils.getPost(id);
		request.setAttribute("post", post);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/updatePost.jsp");
		dispatcher.forward(request, response);
		
		postsList(request, response);
	}
	
	/**
	 * DELETE A POST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		postDbUtils.deletePost(id);
		

		postsList(request, response);
	}

	/**
	 * SEARCH FOR A POST
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void searchPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("search");
		
		List<Post> posts = postDbUtils.searchPosts(data, false);
		
		// Keep track of current page number
		int noOfLines = postDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("POST_LIST", posts);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/postList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * ADD A POST AND ALERT
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void addPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String postName = request.getParameter("postName");
		String img_url = request.getParameter("img_url");
		String desc_url = request.getParameter("desc_url");

		Post newPost = new Post(postName, img_url, desc_url);
		String isSuccess = postDbUtils.addPost(newPost);
		
		
		request.setAttribute("isSuccess", isSuccess);
		
		postsList(request, response);
	}

	/**]
	 * 	GET THE TOTAL NUMBER OF PAGES
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
	 * GET LIST OF THE PAGE DIVIDED BY PAGE AND RETUNR TO JSP
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void postsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoString != null) {
			pageNo = Integer.parseInt(pageNoString);
		}
		
		int pageOffset = getPageOffset(pageNo);
		List<Post> posts = postDbUtils.getPosts(numOfLinesPerPage, pageOffset);
		int noOfLines = postDbUtils.getNoOfLines();
		int noOfPages = getNoOfPages(noOfLines);
		
		request.setAttribute("POST_LIST", posts);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("noOfPages", noOfPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/postList.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * GO TO THE ADMIN PAGE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void postsManagement(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Backend/admin.jsp");
		dispatcher.forward(request, response);
	}

}
