package cn.infop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.infop.dao.OptionDao;

public class SystemLogo extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String system_logo = new OptionDao().getValue("system_logo");
		final JspWriter out = getJspContext().getOut();
		out.println(system_logo);
	}

}
