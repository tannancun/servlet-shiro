package cn.infop.controller.setting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import cn.infop.dao.OptionDao;


@WebServlet("/console/set-global.jsp")
public class SetGlobal extends HttpServlet {

	private static final long serialVersionUID = 1239213091452528387L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OptionDao dao = new OptionDao();

		request.setAttribute("console_title", dao.getValue("console_title"));
		request.setAttribute("system_name", dao.getValue("system_name"));
		request.setAttribute("copyright", StringEscapeUtils.unescapeHtml4(dao.getValue("copyright")));
		request.setAttribute("system_logo", dao.getValue("system_logo"));
		request.setAttribute("favicon", dao.getValue("favicon"));

		request.getRequestDispatcher("/admin/setting/global.jsp").forward(request, response);
	}

}
