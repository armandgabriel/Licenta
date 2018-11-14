package com.licenta.servletControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.licenta.db.manager.HandlerDBConnection;
import com.licenta.db.manager.HandlerDBConnectionManager;
import com.licenta.entity.User;


/**
 * Servlet implementation class LoginPageController
 */
public class LoginPageController extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, String> map = new HashMap<String, String>();
	final private String METHOD = "GET";
	final private String ACTION = "/Licenta/parts/content/logoutServlet.jsp";
    final private String NEXTPAGE = "/Licenta/parts/content/main.jsp";
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
		String userNameFromCookie = "null init";
		String user = map.get("user");
		String pass = map.get("password");
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
	         out.println("<p><strong>WELCOME: " + map.get("cookie") + "</strong></p>");
	         out.println("<a href='"+NEXTPAGE+"'>Continue</a>");
	         out.println("<a href='"+ACTION+"'>Logout</a>");
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
		System.out.println("Call after LoginPageFilter");
		String user = request.getParameter("inputEmail");
		String pass = request.getParameter("inputPassword");
		// Create Session.
		Object objM = request.getSession().getAttribute("dbManager");
		HandlerDBConnection manager = (HandlerDBConnectionManager) objM;
		System.out.println("MANAGER STATUS: " + manager.getConnectionStatus());
		if(manager.checkUser(user,pass)) {
			User us = manager.getLoggedUserData();
			Cookie loginCookie = new Cookie("user", us.getName());
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			map.put("user", user);
			map.put("name", us.getName());
			map.put("pass", pass);
			map.put("cookie", loginCookie.getValue());
			doGet(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Incorect Username or Password!');");
			out.println("</script>");
			response.sendRedirect("parts/content/intro.html");
		}
		
		/*
		
		if(user.equals("armand.camner@gmail.com") && pass.equals("1234")) {
			Cookie loginCookie = new Cookie("user", user);
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			map.put("user", user);
			map.put("pass", pass);
			
			
			doGet(request, response);
		}*/
		
		
		
	}

}
