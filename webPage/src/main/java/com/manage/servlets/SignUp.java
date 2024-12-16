package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Date;
import java.util.UUID;

import com.manage.bean.Admin;
import com.manage.bean.User;
import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class SingUp
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String role = request.getParameter("role");
		String mail = request.getParameter("mail");
		String username = request.getParameter("username");
//		String password = request.getParameter("spassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String company = request.getParameter("company");
//		Date dob=request.getParameter("dob");

		if (role.equalsIgnoreCase("admin")) {
			Admin bean = new Admin(mail, username, confirmPassword, company);
			int i=AdminDao.save(bean);
			if (i > 0) {
				out.println("<html><body><h3>Sign-up successful! Please wait to get verified.</h3></body></html>");
			}else {
				out.println("<html><body><h3>Something went wrong. Please try again later</h3></body></html>");
			}
		} else {
			String token = UUID.randomUUID().toString();
			User bean = new User(mail, username, confirmPassword, company, token);
			int i = UserDao.save(bean);
			if (i > 0) {
				out.println("<html><body><h3>Sign-up successful! Please check your email to verify your account.</h3></body></html>");
			}else {
				out.println("<html><body><h3>Something went wrong. Please try again later</h3></body></html>");
			}
			
		}
		request.getRequestDispatcher("index.jsp").include(request, response);

	}

}
