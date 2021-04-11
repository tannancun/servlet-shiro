package cn.infop.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout.do")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 7692344177081751747L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getRequestDispatcher("/login-page.jsp").forward(request, response);
	}

}
