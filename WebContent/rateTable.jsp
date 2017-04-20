<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Reach your aim</title>
</head>
<c:import url="motivation.jsp"></c:import>
	<a href="${pageContext.request.contextPath}/Protected/Cabinet.jsp">Назад</a>
	
	<br><h3><c:out value="Кто в группах самый молодец:"></c:out></h3>
	
<body>
	<c:choose>
		<c:when test="${sessionScope.rateUsersInGroup ne null}">
		<br><table border=1> 
					<tr>
						<td>Группа</td>
						<td>Имя</td>
						<td>Решенные</td>
						<td>Не решённые</td>
					</tr>
		<c:forEach var="data" items="${sessionScope.rateUsersInGroup}">
			<!-- Iterating over data for current WebUser to retrieve actual aims-->
			<c:forEach var="group" items="${data}">
					<tr>
						<td>${group.webGroup.name}</td>
						<td>${group.webUser.userId}</td>
						<td>${group.webUser.solved}</td>
						<td>${group.webUser.failed}</td>
					</tr>
			</c:forEach>
		</c:forEach>	
			</table>
		</c:when>
		<c:otherwise>У вас нет групп, что бы определить самого самого</c:otherwise>
	</c:choose>			
</body>
</html>