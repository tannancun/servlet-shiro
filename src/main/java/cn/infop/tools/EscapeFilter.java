package cn.infop.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EscapeFilter implements Filter {
    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
    
    @Override
	public void doFilter(ServletRequest request, 
	                     ServletResponse response, 
	                     FilterChain chain) 
	                          throws IOException, ServletException {
		HttpServletRequest requestWrapper=new EscapeRequestWrapper((HttpServletRequest)request);
		chain.doFilter(requestWrapper, response);
	}
	
    @Override
    public void destroy() {}
}
