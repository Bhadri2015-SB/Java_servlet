package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
//import java.io.PrintWriter;

import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class ProfilePic
 */
@WebServlet("/profilePic")
@MultipartConfig
public class ProfilePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		String role=(String)session.getAttribute("role");
		String mail = (String)session.getAttribute("mail");
		Part part = request.getPart("image");
		InputStream is = part.getInputStream();
		
		if(part != null) {
			if(role.equalsIgnoreCase("user")) {
				UserDao.updatePic(mail, is);
//				out.println("Profile picture updates");
				response.sendRedirect("details");
			}else {
				AdminDao.updatePic(mail, is);
//				out.println("Profile picture updates");
				response.sendRedirect("details");
			}
		}
		
	}

}
