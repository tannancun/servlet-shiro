package cn.infop.controller.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/console/menu-add.jsp")
public class MenuAdd extends HttpServlet {

	private static final long serialVersionUID = -7867725983207545553L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/admin/security/menu/add.jsp").forward(request, response);
	}

}
