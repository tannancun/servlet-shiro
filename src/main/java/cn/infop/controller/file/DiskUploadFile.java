package cn.infop.controller.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;

import cn.infop.dao.FileDao;
import cn.infop.dao.OptionDao;
import cn.infop.tools.ConstantUtils;
import cn.infop.tools.JsonUtils;

/**
 *
 * @author wsh
 */
@MultipartConfig
@WebServlet(name = "UploadFile", urlPatterns = { "/console/upload-file.do" })
public class DiskUploadFile extends HttpServlet {

	private static final long serialVersionUID = -4027072175142425132L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("file");
		InputStream is = part.getInputStream();
		String md5 = DigestUtils.md5Hex(is);
		saveFileToDisk(req, part, md5);
		Map<String, String> map = new HashMap<>();
		map.put("link", ConstantUtils.DISPLAY_FILE + md5);// 返回的是display-file.jsp?file=md5
		JsonUtils.printJson(resp, map);
	}

	/**
	 *
	 * @param request
	 * @param part
	 * @param md5
	 * @throws IOException
	 */
	private void saveFileToDisk(HttpServletRequest request, Part part, String md5) throws IOException {
		OptionDao dao = new OptionDao();
		String folder = dao.getValue(ConstantUtils.FILE_PATH);
		File folderPath = new File(folder);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}

		File file = new File(folder, md5);
		if (file.exists()) {
			addFileInfo(part, md5);
		} else {
			Files.copy(part.getInputStream(), file.toPath());
			addFileInfo(part, md5);
		}
	}

	private void addFileInfo(Part part, String md5) throws IOException {
		FileDao fileDao = new FileDao();
		cn.infop.mode.File obj = fileDao.findByMd5(md5);
		if (ObjectUtils.isEmpty(obj)) {
			cn.infop.mode.File file = new cn.infop.mode.File();
			file.setMd5(md5);
			file.setFilename(getFilename(part));
			file.setFiletype(part.getContentType());
			fileDao.save(file);
		}
	}

	private String getFilename(Part part) throws IOException {
		String header = part.getHeader("content-disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		return filename;
	}
}
