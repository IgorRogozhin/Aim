<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css" />
<link rel="stylesheet" href="css/styles.css" />
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
	<c:import url="motivation.jsp"></c:import>
	<c:out value="${pageContext.exception.message}"></c:out>
	<c:out value="${param.ex}"></c:out>

	<c:choose>
		<c:when test="${sessionScope.authorized_user ne null}">
			<c:import url="navbar.jsp"></c:import>
		</c:when>
		<c:otherwise>
			<br>
			<a href="index.jsp">Вернуться на главную</a>
		</c:otherwise>
	</c:choose>
	<br>
	<h2 class="attention">Ого, приключилась ошибка!</h2>
	<br>
</body>
</html>
