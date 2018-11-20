package com.licenta.servletControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.licenta.db.manager.HandlerDBConnection;
import com.licenta.db.manager.HandlerDBConnectionManager;
import com.licenta.entity.User;
import com.licenta.utils.EncodeUtil;

/**
 * Servlet implementation class CreateAccountController
 */
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET CA");
		/*PrintWriter out = response.getWriter();
		 try {
	         out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         out.println("<title>Hello, World</title></head>");
	         out.println("<body>");
	         out.println("<p>CREATE ACCOUNT PAGE:</p>");
	         out.println("</body>");
	         out.println("</html>");
	      } finally {
	         out.close();  // Always close the output writer
	      }*/
		response.sendRedirect("parts/content/accountCreationPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST");
		
		//first_name,last_name,display_name,email,password
		
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String mName = request.getParameter("display_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Object objM = request.getSession().getAttribute("dbManager");
		HandlerDBConnection manager = (HandlerDBConnectionManager) objM;
		List<String> params = new ArrayList<String>();
		params.add(fName);
		params.add(lName);
		params.add(mName);
		params.add(email);
		try {
			params.add(EncodeUtil.sign(password));
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		if(processUser(params,manager))
		{
			response.sendRedirect("/Licenta/Welcome");
		}
		
	}
	private boolean processUser(List<String> params, HandlerDBConnection manager)
	{
		User t = new User();
		try {
			t.setAge(new Date()+"");
		} catch (Exception e)
		{
			System.out.println("Date error: " + e.getMessage());
		}
		t.setHistory("no_history:User Creation");
		t.setName(params.get(0)+" " + params.get(2) + " " + params.get(1));
		t.setUserName(params.get(3));
		t.setPassword(params.get(4));
		t.setRole("0");
		manager.signUser(t);
		return true;
	}
}
