package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintWriter;
//import java.sql.Blob;
//import java.sql.SQLException;
import java.util.List;

import com.manage.bean.User;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class AllUsers
 */
@WebServlet("/all_users")
public class AllUsers extends HttpServlet {
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
		out.println("<title>View Users</title>");
		out.println("<link rel='stylesheet' href='navStyle.css'>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navAdmin.html").include(request, response);
//		out.println("<div class='container'>");
		HttpSession session=request.getSession();
		String mail=(String)session.getAttribute("mail");
		
		List<User> list = UserDao.viewAllUsers(mail);
		out.println("<table>");
		out.println("<tr><th>Picture</th><th>Name</th><th>Email</th><th>Company</th><th>Delete</th></tr>");
		
		for(User bean:list){
			out.println("<tr><td><img src='iterativePic?id="+bean.getId()+"'alt=\"\" class=\"profile-picture\"></td><td>"+bean.getName()+"</td><td>"+bean.getEmail()+"</td><td>"+bean.getCompany()+"</td><td><a href='delete_user?id="+bean.getId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
//		out.println("</div>");
        out.println("</body></html>");

	}
	
		
	
}
