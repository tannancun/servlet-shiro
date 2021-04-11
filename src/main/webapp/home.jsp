<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<p>
		Hi
		<shiro:guest>Guest</shiro:guest>
		<shiro:user>
			<%
				//This should never be done in a normal page and should exist in a proper MVC controller of some sort, but for this
			//tutorial, we'll just pull out Stormpath Account data from Shiro's PrincipalCollection to reference in the
			//<c:out/> tag next:

			request.setAttribute("account",
					org.apache.shiro.SecurityUtils.getSubject().getPrincipals().oneByType(java.util.Map.class));
			%>
			<c:out value="${account.username}" />
		</shiro:user>
		! (
		<shiro:user>
			<a href="<c:url value="/logout"/>">Log out</a>
			
			
			<shiro:hasPermission name=""></shiro:hasPermission>
			
			
		</shiro:user>
		<shiro:guest>
			<a href="<c:url value="/login.jsp"/>">Log in</a>
		</shiro:guest>
		)
	</p>
</body>
</html>