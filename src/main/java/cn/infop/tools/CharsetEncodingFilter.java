package cn.infop.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class CharsetEncodingFilter
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
    @WebInitParam(name = "encoding", value = "UTF-8")})
public class CharsetEncodingFilter implements Filter {

    private FilterConfig config;

    public CharsetEncodingFilter() {
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(config.getInitParameter("encoding"));
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.config = fConfig;

    }

}
