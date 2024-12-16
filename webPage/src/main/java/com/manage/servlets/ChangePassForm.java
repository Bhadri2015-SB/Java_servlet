package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ChangePassForm
 */
@WebServlet("/editUser")
public class ChangePassForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>My details</title>");
		out.println("<link rel=\"stylesheet\" href=\"style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("mail");
//		String role=request.getParameter("role");
//		String email=request.getParameter("mail");
		out.println("<div id=\"sign-up-form\" class=\"form-container\" >\r\n"
				+ "      <h2>Change Password</h2>\r\n"
				+ "      <form action=\"passUpdate\" method=\"post\">\r\n"
				+ "			<input type='hidden' name='mail' value='"+email+"'/>"
				+ "        <input type=\"password\" name=\"cpassword\" placeholder=\"Current Password\" required>\r\n"
				+ "        <input type=\"password\" name=\"spassword\" placeholder=\"New Password\" required>\r\n"
				+ "        <input type=\"password\" name=\"confirmPassword\" placeholder=\"Confirm Password\" required>\r\n"
				+ "        <button type=\"submit\">Change</button>\r\n"
				+ "      </form>\r\n"
				+ "    </div>"
				+ "<script src=\"switchForm.js\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	

}
