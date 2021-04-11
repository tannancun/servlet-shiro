package cn.infop.controller.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.infop.dao.MenuDao;
import cn.infop.mode.Menu;

@WebServlet("/console/menu-edit.jsp")
public class MenuEdit extends HttpServlet {

	private static final long serialVersionUID = 1669007384176909709L;
	private static final Logger log = Logger.getLogger(MenuEdit.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		MenuDao dao = new MenuDao();
		Menu menu = dao.findOneById(Integer.parseInt(id));
		Menu pmenu = dao.findOneById(menu.getPid());
		log.debug(pmenu);
		request.setAttribute("menu", menu);
		request.setAttribute("pmenu", pmenu);
		request.getRequestDispatcher("/admin/security/menu/edit.jsp").forward(request, response);
	}

}
