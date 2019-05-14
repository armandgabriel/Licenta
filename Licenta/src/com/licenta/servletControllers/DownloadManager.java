package com.licenta.servletControllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadManager
 */
//@WebServlet(description = "Download Manager for Jar File.", urlPatterns = { "/DownloadManager" })
public class DownloadManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("APPLICATION/OCTET-STREAM");
		PrintWriter out = response.getWriter();
		String fileName = "ApplicationV01";
		String fileExtension = ".jar";
		String filePath = "C:\\Users\\ARMANDGABRIELCAMNER\\git";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + fileExtension +"\"");
		FileInputStream fis = new FileInputStream(filePath + "\\" + fileName + fileExtension);
		int i;
		while ((i=fis.read())!=-1) {
			out.write(i);
		}
		fis.close();
		out.close();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

}
