<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Reach your aim</title>
</head>
<body>
	<c:import url="../motivation.jsp"></c:import>
	
	<c:if test="${requestScope.success ne null}"><h2>${requestScope.success}</h2></c:if>
	
	<h3>Кабинет</h3>
	<c:import url="../navbar.jsp"></c:import>
	<br><h3><c:out value="Твои текущие задачи"></c:out></h3>
	<c:choose>
		<c:when test="${sessionScope.aimData ne null}">
			<c:import url="../aimsByDeadlineTable.jsp"></c:import>
		</c:when>
		<c:otherwise><br><h3>Здесь будут ваши текущие задачи, если вы их перед собой поставите</h3></c:otherwise>
	</c:choose>
	
</body>
</html>