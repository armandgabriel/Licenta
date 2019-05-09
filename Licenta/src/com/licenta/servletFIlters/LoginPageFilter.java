package com.licenta.servletFIlters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginPageFilter
 */
public class LoginPageFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginPageFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("LoginPageFilter pass before Call of LoginPageController!");
		String user = request.getParameter("inputEmail");
		String pass = request.getParameter("inputPassword");
		if (user != null && !user.isEmpty() && pass != null && !pass.isEmpty()) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession(false);
			if(session!=null) {
				session.invalidate();
			} 
			res.sendRedirect("Welcome");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
