<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css"/>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
<c:choose>
	<c:when test="${pageContext.request.contextPath eq initParam.defaultWebBase}">
		<!-- use base links -->
		<ul style="list-style-type:none; padding:0px; margin:0px 0px 0px 0px;">
			<li id="aimLink" class="link"><a href="${initParam.baseURL}/Protected/aim.jsp">Задачи</a></li>
			<li id="groupLink" class="link"><a href="${initParam.baseURL}/Protected/group.jsp">Группы</a></li>
			<li id="signoutLink" class="link"><a href="${initParam.baseURL}/invalidatesessionandremovecookies.do">Выйти</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<!-- rely on context -->
		<ul style="list-style-type:none; padding:0px; margin:0px 0px 0px 0px;">
			<li id="aimLink1" class="link"><a href="${pageContext.request.contextPath}/Protected/aim.jsp">Задачи</a></li>
			<li id="groupLink1" class="link"><a href="${pageContext.request.contextPath}/Protected/group.jsp">Группы</a></li>
			<li id="signoutLink1" class="link"><a href="${pageContext.request.contextPath}/invalidatesessionandremovecookies.do">Выйти</a></li>
		</ul>
	</c:otherwise>
</c:choose>
</body>
</html>