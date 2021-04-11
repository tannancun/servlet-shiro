package cn.infop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/console/home.jsp")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1217979829976219148L;
	
	private static final Logger log = Logger.getLogger(Home.class);

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}

}