package cn.infop.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;

import cn.infop.dao.FileDao;
import cn.infop.dao.OptionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;

public class DiskFileUtils {
	/**
	 *
	 * @param request
	 * @param part
	 * @param md5
	 * @throws IOException
	 */
	private static void saveFileToDisk(HttpServletRequest request, Part part, String md5) throws IOException {
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

	private static void addFileInfo(Part part, String md5) throws IOException {
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

	private static String getFilename(Part part) throws IOException {
		String header = part.getHeader("content-disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		return filename;
	}

	/**
	 * 将part中的文件流保存到磁盘
	 *
	 * @param request
	 * @param part
	 * @param folder   目标目录
	 * @param filename 文件名
	 * @return
	 * @throws IOException
	 */
	public static String writeTo(HttpServletRequest request, Part part) throws IOException {
		InputStream is = part.getInputStream();
		String md5 = DigestUtils.md5Hex(is);
		saveFileToDisk(request, part, md5);
		return ConstantUtils.DISPLAY_FILE + md5;// 返回的是display-file.jsp?file=md5
	}
}
