package cn.infop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.infop.dao.OptionDao;

import java.io.IOException;

public class SystemName extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String system_name = new OptionDao().getValue("system_name");
		final JspWriter out = getJspContext().getOut();
		out.println(system_name);
	}

}
