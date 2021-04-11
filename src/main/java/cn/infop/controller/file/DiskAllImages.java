package cn.infop.controller.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.infop.dao.FileDao;
import cn.infop.tools.ConstantUtils;
import cn.infop.tools.JsonUtils;

@WebServlet(name = "AllImages", urlPatterns = { "/console/image-files.do" })
public class DiskAllImages extends HttpServlet {

	private static final long serialVersionUID = 7274056630358305797L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileDao fileDao = new FileDao();
		List<cn.infop.mode.File> fileInfos = fileDao.findImages();
		List<Map<String, String>> list = new ArrayList<>();
		fileInfos.forEach((f) -> {
			Map<String, String> map = new HashMap<>();
			map.put("url", ConstantUtils.DISPLAY_FILE + f.getMd5());
			map.put("name", f.getFilename());
			map.put("id", f.getMd5());
			list.add(map);
		});
		JsonUtils.printJson(response, list);
	}

}
