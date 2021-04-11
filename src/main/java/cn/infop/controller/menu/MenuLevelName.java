package cn.infop.controller.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.infop.dao.MenuDao;
import cn.infop.mode.Menu;
import cn.infop.tools.JsonUtils;

@WebServlet("/console/menu-level-name.do")
public class MenuLevelName extends HttpServlet {

	private static final long serialVersionUID = 8939348254891866121L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Menu> list = new MenuDao().selectMenuTree(0);
		JsonUtils.printJson(response, list);
	}

}
