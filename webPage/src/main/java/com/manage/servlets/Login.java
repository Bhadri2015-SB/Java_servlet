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

import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
		String role = request.getParameter("role");
		String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        
        out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Home</title>");
		out.println("<link rel='stylesheet' href='navStyle.css'>");
//		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head>");
		out.println("<body>");
//		out.println("<div class='container'");
		
        if(role.equalsIgnoreCase("admin")) {
        	if(AdminDao.athentication(mail, password)) {
        		HttpSession session = request.getSession();
        		session.setAttribute("mail",mail);
        		session.setAttribute("role", role);
    			request.getRequestDispatcher("navAdmin.html").include(request, response);
        	}else {
        		out.println("<h3>Username or password error</h3>");
    			response.sendRedirect("index.jsp");
        	}
        }else {
        	if(UserDao.athentication(mail, password)) {
        		HttpSession session = request.getSession();
        		session.setAttribute("mail",mail);
        		session.setAttribute("role", role);
    			request.getRequestDispatcher("navUser.html").include(request, response);
        	}else {
        		out.println("<h3>Username or password error</h3>");
    			response.sendRedirect("index.jsp");
        	}
        }
        
        
        out.println("</body></html>");
	}

}
