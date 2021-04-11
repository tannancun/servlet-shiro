package cn.infop.controller.menu;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.infop.dao.MenuDao;
import cn.infop.tools.ConstantUtils;

@WebServlet("/console/menu-save.do")
public class MenuSave extends HttpServlet {

	private static final long serialVersionUID = -472165658204717758L;

	private static final Logger log = Logger.getLogger(MenuAdd.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] fields = new String[] { "name", "symbol", "pid" };
		Object[] values = new Object[] { request.getParameter("name"), request.getParameter("symbol"),
				request.getParameter("pid") };
		MenuDao dao = new MenuDao();
		try {
			dao.save(fields, values);
			dao.updateLevelName();
		} catch (SQLException e) {
			log.error("Menu save failure", e);
		}
		request.setAttribute("msg", ConstantUtils.MSG_SAVED);
		request.getRequestDispatcher("/console/menu-list.jsp").forward(request, response);
	}

}
