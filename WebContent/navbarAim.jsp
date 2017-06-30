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
<br>
<c:choose>
	<c:when test="${pageContext.request.contextPath eq initParam.defaultWebBase}">
		<!-- use base links -->
		<ul class="zeroStyle">
			<li id="addLink" class="link"><a href="${initParam.baseURL}/Protected/addAim.jsp">Поставить задачу</a></li>
			<li id="archiveLink" class="link"><a href="${initParam.baseURL}/Protected/archive.jsp">Архив</a></li>
			<li id="signoutLink" class="link"><a href="${initParam.baseURL}/Protected/Cabinet.jsp">Кабинет</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<!-- rely on context -->
		<ul class="zeroStyle">
			<li id="addLink" class="link"><a href="${pageContext.request.contextPath}/Protected/addAim.jsp">Поставить задачу</a></li>
			<li id="archiveLink" class="link"><a href="${pageContext.request.contextPath}/Protected/archive.jsp">Архив</a></li>
			<li id="signoutLink" class="link"><a href="${pageContext.request.contextPath}/Protected/Cabinet.jsp">Кабинет</a></li>
		</ul>
	</c:otherwise>
</c:choose>
<br>
</body>
</html>