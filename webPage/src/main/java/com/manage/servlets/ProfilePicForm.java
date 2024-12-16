package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ProfilePicForm
 */
@WebServlet("/profilePicForm")
@MultipartConfig
public class ProfilePicForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>My details</title>");
		out.println("<link rel=\"stylesheet\" href=\"navStyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		
		out.println("<form action=\"profilePic\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
				+ "	<table border=\"2\" align=\"center\" width=\"50%\">\r\n"
				+ "	<tr>\r\n"
				+ "	<th align=\"center\" bgcolor=\"blue\" style=\"color:white\" colspan=\"5\"> <h2>Upload image</h2>\r\n"
				+ "	</th>\r\n"
				+ "	</tr>\r\n"
				+ "	<tr>\r\n"
				+ "	<th align=\"right\">Select Image:</th>\r\n"
				+ "	<td><input type=\"file\" name=\"image\"/></td>\r\n"
				+ "	</tr>\r\n"
				+ "	<tr>\r\n"
				+ "	<td><input type=\"submit\" value=\"Upload\"></td>\r\n"
				+ "	<td><input type=\"reset\" value=\"Reset\"></td>\r\n"
				+ "	</tr>\r\n"
				+ "	</form>");
		out.println("</div>");
		out.println("</body></html>");
	}

	
}
