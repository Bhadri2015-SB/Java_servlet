package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.io.PrintWriter;

import com.manage.bean.Admin;
import com.manage.bean.User;
import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class User
 */
@WebServlet("/details")
public class MyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>My details</title>");
		out.println("<link rel='stylesheet' href='navStyle.css'>");
		out.println("</head>");
		out.println("<body>");
//		out.println("<div class='container'>");
		
//		String role=request.getParameter("role");
//		String email=request.getParameter("mail");
//		System.out.println(role+" " + email);
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("mail");
		String role=(String)session.getAttribute("role");
//		System.out.println(email+" "+role);
		
		if(role.equalsIgnoreCase("admin")) {
			request.getRequestDispatcher("navAdmin.html").include(request, response);
			Admin bean=AdminDao.myDetail(email);
			out.println("<div class='container'>");
//			out.println("<p>ID: "+bean.getId());
			out.println("</p><p>Name: "+bean.getName());
			out.println("</p><p>Email: "+bean.getEmail());
			out.println("</p><p>Company Name: "+bean.getCompany());
			out.println("</p><p><button><a href=\"editUser\">Change Password</a></button");
			out.println("</p><p><button><a href=\"profilePicForm\">Change Picture</a></button");
		}else {
			request.getRequestDispatcher("navUser.html").include(request, response);
			User bean=UserDao.myDetail(email);
			out.println("<div class='container'>");
//			out.println("<p>ID: "+bean.getId());
			out.println("</p><p>Name: "+bean.getName());
			out.println("</p><p>Email: "+bean.getEmail());
			out.println("</p><p>Company Name: "+bean.getCompany());
			out.println("</p><p><button><a href=\"editUser\">Change Password</a></button");
			out.println("</p><p><button><a href=\"profilePicForm\">Change Picture</a></button");
		}
		
		
		
		
		out.println("</p></div>");
		out.println("</body></html>");
		
	}

}
