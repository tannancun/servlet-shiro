package cn.infop.controller.setting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import cn.infop.dao.OptionDao;
import cn.infop.tools.ConstantUtils;
import cn.infop.tools.DiskFileUtils;

@MultipartConfig
@WebServlet("/console/set-global-update.jsp")
public class SetGlobalUpdate extends HttpServlet {

	private static final long serialVersionUID = -3416425826514338340L;

	private String save(HttpServletRequest request, String key) throws IOException, ServletException {
		Part value = request.getPart(key);
		String url = "";
		long logo_size = value.getSize();
		if (logo_size > 0) {
			url = DiskFileUtils.writeTo(request, value);
		}
		return url;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String console_title = request.getParameter("console_title");
		String system_name = request.getParameter("system_name");
		String copyright = request.getParameter("copyright");
		String logo_url = save(request, "system_logo");
		String favicon_url = save(request, "favicon");

		OptionDao dao = new OptionDao();
		dao.update("console_title", console_title);
		dao.update("system_name", system_name);
		dao.update("copyright", copyright);
		if (StringUtils.isNotBlank(logo_url))
			dao.update("system_logo", logo_url);
		if (StringUtils.isNotBlank(favicon_url))
			dao.update("favicon", favicon_url);

		request.setAttribute("msg", ConstantUtils.MSG_SAVED);
		request.getRequestDispatcher("/console/set-global.jsp").forward(request, response);
	}

}
