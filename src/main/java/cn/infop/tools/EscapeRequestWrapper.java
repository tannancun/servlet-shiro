package cn.infop.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;

public class EscapeRequestWrapper extends HttpServletRequestWrapper {

	public EscapeRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = getRequest().getParameter(name);
		return StringEscapeUtils.escapeHtml4(value);

	}
}
