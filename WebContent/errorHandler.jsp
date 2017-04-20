<%@ page language="java" 
	contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"
    isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" 
	content="text/html; charset=utf-8">

  <title>Reach your aim</title>
 </head>
<body>
	<c:import url="motivation.jsp"></c:import>
	
				
			<br><h2>Ого, приключилась ошибка!</h2><br>
			
			<c:out value="${pageContext.exception.message}"></c:out> 
			<c:out value="${param.ex}"></c:out> 
					
		<c:choose>
			<c:when test="${sessionScope.authorized_user ne null}">
				<br><a href="${pageContext.request.contextPath}/Protected/Cabinet.jsp">Вернуться в кабинет</a>
			</c:when>
			<c:otherwise>
				<br><a href="index.jsp">Вернуться на главную</a>
			</c:otherwise>
		</c:choose>	
	
</body>
</html>
