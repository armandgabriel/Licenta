package com.licenta.servletControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.licenta.db.manager.HandlerDBConnection;
import com.licenta.db.manager.HandlerDBConnectionManager;

/**
 * Servlet implementation class WelcomeController
 */
public class WelcomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		final HandlerDBConnection window = new HandlerDBConnectionManager();
		window.initConnection();
		System.out.println(window.getConnectionStatus());
		try {
			// Delete attribute dbManager if exists
			if (request.getSession().getAttribute("dbManager") != null) {
				// request.getSession()out;
				HttpSession session = request.getSession(false);
				session.invalidate();
			}
			request.getSession().setAttribute("dbManager", window);
			// AutoLogin 
			Cookie[] cookies = null; 
			if((cookies = request.getCookies())!=null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user")) {
						System.out.println("Welcome page cookie: " + cookie.getName() + " value: " + cookie.getValue());
						cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
						//response.sendRedirect("/Licenta/lContr");
					}
				}
			}
			response.sendRedirect("parts/content/intro.html");
		} catch (Exception e) {
			System.out.println("e: " + e.getMessage());
		} finally {
			out.close();
		}
	}
}
