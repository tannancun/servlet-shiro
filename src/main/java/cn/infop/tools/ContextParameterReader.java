package cn.infop.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.infop.dao.OptionDao;

/**
 * Application Lifecycle Listener implementation class Csss
 *
 */
@WebListener
public class ContextParameterReader implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		context.setAttribute("year", sdf.format(new Date()));
		
		String absoluteDiskPath = context.getRealPath(ConstantUtils.RELATIVE_WEB_PATH);
		OptionDao dao = new OptionDao();
		dao.update(ConstantUtils.FILE_PATH, absoluteDiskPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
