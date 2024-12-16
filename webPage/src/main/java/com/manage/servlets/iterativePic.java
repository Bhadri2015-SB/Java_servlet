package com.manage.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import com.manage.dao.UserDao;

/**
 * Servlet implementation class iterativePic
 */
@WebServlet("/iterativePic")
public class iterativePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		byte[] img=UserDao.getIteImage(id);
		response.setContentType("image/jpeg"); 
		OutputStream os = response.getOutputStream(); 
		os.write(img); 
		os.flush(); 
		os.close();
	}


}
