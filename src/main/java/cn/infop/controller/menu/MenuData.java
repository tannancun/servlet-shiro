package cn.infop.controller.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.infop.dao.MenuDao;
import cn.infop.datatables.DataTablesRequest;
import cn.infop.datatables.DataTablesResponse;
import cn.infop.mode.Menu;
import cn.infop.tools.JsonUtils;

@WebServlet("/console/menu-data.do")
public class MenuData extends HttpServlet {

	private static final long serialVersionUID = 8676320546694629724L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataTablesResponse<Menu> pd = new MenuDao().findAll(new DataTablesRequest(request),
				new String[] { "oid", "levelname", "symbol" });
		JsonUtils.printJson(response, pd);
	}

}
