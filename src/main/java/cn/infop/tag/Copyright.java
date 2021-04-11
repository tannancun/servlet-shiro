package cn.infop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.text.StringEscapeUtils;

import cn.infop.dao.OptionDao;

import java.io.IOException;

public class Copyright extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String copyright = new OptionDao().getValue("copyright");
		final JspWriter out = getJspContext().getOut();
		out.println(StringEscapeUtils.unescapeHtml4(copyright));
	}

}
