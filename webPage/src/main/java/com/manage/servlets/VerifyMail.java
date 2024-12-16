package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.manage.dao.UserDao;

/**
 * Servlet implementation class VerifyMail
 */
@WebServlet("/verify")
public class VerifyMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	     
		String token = request.getParameter("token");
		int state = UserDao.verifyMail(token);
		if(state>0) {
            out.println("<html><body><h3>Your account has been verified. You can now log in.</h3></body></html>");
            request.getRequestDispatcher("index.jsp").include(request, response);	
		}else {
			out.println("<html><body><h3>Something went wrong.</h3></body></html>");
		}
	}

}
