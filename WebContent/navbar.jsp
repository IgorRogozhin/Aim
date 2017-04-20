<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Reach your aim</title>
</head>
<body>
<br>
<c:choose>
	<c:when test="${pageContext.request.contextPath eq initParam.defaultWebBase}">
		<!-- use base links -->
		<ul style="list-style-type:none; padding:0px; margin:0px 0px 0px 0px;">
			<li id="aimLink"><a href="${initParam.baseURL}/Protected/aim.jsp">Задачи</a></li>
			<li id="groupLink"><a href="${initParam.baseURL}/Protected/group.jsp">Группы</a></li>
			<li id="signoutLink"><a href="${initParam.baseURL}/invalidatesessionandremovecookies.do">Выйти</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<!-- rely on context -->
		<ul style="list-style-type:none; padding:0px; margin:0px 0px 0px 0px;">
			<li id="aimLink"><a href="${pageContext.request.contextPath}/Protected/aim.jsp">Задачи</a></li>
			<li id="groupLink"><a href="${pageContext.request.contextPath}/Protected/group.jsp">Группы</a></li>
			<li id="signoutLink"><a href="${pageContext.request.contextPath}/invalidatesessionandremovecookies.do">Выйти</a></li>
		</ul>
	</c:otherwise>
</c:choose>
<br>
</body>
</html>