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
	<c:import url="../motivation.jsp"></c:import>
	<c:import url="../navbarGroup.jsp"></c:import>
	
	<br><h3><c:out value="Текущие задачи в группах:"></c:out></h3>
	
	
	<c:choose>
		<c:when test="${sessionScope.userAimGroupData ne null}">
			<c:import url="../aimsInGroupTable.jsp"></c:import>
		</c:when>
		<c:otherwise><br><h3>В группах в которых вы состоите нет задач, нехорошо!</h3></c:otherwise>
	</c:choose>
</body>
</html>