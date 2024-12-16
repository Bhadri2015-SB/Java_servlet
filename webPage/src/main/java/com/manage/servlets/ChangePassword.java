package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/passUpdate")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String role=(String)session.getAttribute("role");
		String mail = request.getParameter("mail");
		String cpassword = request.getParameter("cpassword");
		String password = request.getParameter("spassword");
		String conassword = request.getParameter("confirmPassword");
		
		if(role.equalsIgnoreCase("user")) {
			if(UserDao.athentication(mail, cpassword)) {
				if(password.equals(conassword))
					UserDao.updatePass(mail,password);
				response.sendRedirect("details");
			}else {
				out.println("Password incorrect");
				request.getRequestDispatcher("editUser").include(request, response);
			}
		}else {
			if(AdminDao.athentication(mail, cpassword)) {
				if(password.equals(conassword))
					AdminDao.updatePass(mail,password);
				response.sendRedirect("details");
			}else {
				out.println("Password incorrect");
				request.getRequestDispatcher("editUser").include(request, response);
			}
		}
		
	}

}
