package com.licenta.servletControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.licenta.db.manager.HandlerDBConnection;
import com.licenta.db.manager.HandlerDBConnectionManager;

/**
 * Servlet implementation class LoginPageController
 */
public class LoginPageController extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, String> map = new HashMap<String, String>();
    /**
     * Default constructor. 
     */
    public LoginPageController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Object objM = request.getSession().getAttribute("dbManager");
		HandlerDBConnection manager = (HandlerDBConnectionManager) objM;
		 try {
	         out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         out.println("<title>Hello, World</title></head>");
	         out.println("<body>");
	         out.println("<h1>Hello, world!</h1>");  // says Hello
	         // Echo client's request information
	         out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
	         out.println("<p>Protocol: " + request.getProtocol() + "</p>");
	         out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
	         out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
	         // Generate a random number upon each request
	         out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
	         out.println("<p>User: <strong>" + map.get("user") +"</strong></p>");
	         out.println("<p>Pass: <strong>" + map.get("pass") +"</strong></p>");
	         out.println("<p>Manager: <strong>"+manager.getConnectionStatus()+"</strong></p>");
	         out.println("</body>");
	         out.println("</html>");
	      } finally {
	         out.close();  // Always close the output writer
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("inputEmail");
		String pass = request.getParameter("inputPassword");
		map.put("user", user);
		map.put("pass", pass);
		doGet(request, response);
	}

}