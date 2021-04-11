package cn.infop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.infop.dao.OptionDao;

import java.io.IOException;

public class ConsoleTitle extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String console_title = new OptionDao().getValue("console_title");
		final JspWriter out = getJspContext().getOut();
		out.println(console_title);
	}

}
