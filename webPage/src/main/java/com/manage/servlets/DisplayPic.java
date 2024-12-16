package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;

import com.manage.dao.AdminDao;
import com.manage.dao.UserDao;

/**
 * Servlet implementation class DisplayPic
 */
@WebServlet("/displayPic")
public class DisplayPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role=(String)session.getAttribute("role");
		String mail=(String)session.getAttribute("mail");
		if(role.equalsIgnoreCase("admin")) {
			byte[] img=AdminDao.getImage(mail);
			response.setContentType("image/jpeg"); 
			OutputStream os = response.getOutputStream(); 
			os.write(img); 
			os.flush(); 
			os.close();
		}else {
			byte[] img=UserDao.getImage(mail);
			response.setContentType("image/jpeg"); 
			OutputStream os = response.getOutputStream(); 
			os.write(img); 
			os.flush(); 
			os.close();
		}
	}

	

}
