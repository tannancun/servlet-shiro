package cn.infop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.infop.dao.OptionDao;

public class Favicon extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String favicon = new OptionDao().getValue("favicon");
		final JspWriter out = getJspContext().getOut();
		out.println(favicon);
	}

}
