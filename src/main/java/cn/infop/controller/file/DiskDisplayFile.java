package cn.infop.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import cn.infop.dao.FileDao;
import cn.infop.dao.OptionDao;
import cn.infop.tools.ConstantUtils;

/**
 *
 * @author wsh
 */
@WebServlet(name = "DisplayFile", urlPatterns = {"/display-file.jsp"})
public class DiskDisplayFile extends HttpServlet {

	private static final long serialVersionUID = -8896857371801626178L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String md5 = req.getParameter("file");
        if (StringUtils.isNotEmpty(md5)) {
        	OptionDao dao = new OptionDao();
        	FileDao fileDao = new FileDao();
        	cn.infop.mode.File fileInfo  = fileDao.findByMd5(md5);
            String folder = dao.getValue(ConstantUtils.FILE_PATH);
            FileInputStream fis = null;
            String filePath = folder + "/" + md5;
            response.setContentType(fileInfo.getFiletype());
            response.addHeader("Content-Disposition", "attachment; filename="+fileInfo.getFilename());
            ServletOutputStream output = response.getOutputStream();
            try {
                fis = new FileInputStream(new File(filePath));
                byte[] buf = new byte[1024];
                int i;
                while ((i = fis.read(buf)) != -1) {
                    output.write(buf, 0, i);
                }
                output.flush();
            } finally {
                fis.close();
                output.close();
            }
        }

    }

}

