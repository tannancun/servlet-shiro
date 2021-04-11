package cn.infop.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Login.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		log.debug(username);
		log.debug(password);

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		Subject currentUser = SecurityUtils.getSubject();
		
		//org.apache.shiro.authc.credential.Sha512CredentialsMatcher
		

		try {
			currentUser.login(token);
			// if no exception, that's it, we're done!
			log.debug("验证成功");
			request.getRequestDispatcher("/console/home.jsp").forward(request, response);
			
		} catch (UnknownAccountException uae) {
			// username wasn't in the system, show them an error message?
			log.debug("用户名不存在");
		} catch (IncorrectCredentialsException ice) {
			// password didn't match, try again?
			log.debug("密码不匹配");
		} catch (LockedAccountException lae) {
			// account for that username is locked - can't login. Show them a message?
			log.debug("账户被锁定");
		} catch (ExcessiveAttemptsException eae) {
			// account for that username is locked - can't login. Show them a message?
			log.debug("请过10分钟后再登录");
		} catch (AuthenticationException ae) {
			
			
			log.debug("验证异常");
			response.sendRedirect("login.jsp");
		}

	}

}
