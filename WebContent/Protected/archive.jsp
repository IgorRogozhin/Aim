<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reach your aim</title>
</head>
<body>
	<c:import url="../motivation.jsp"></c:import>
	<a href="${initParam.baseURL}/Protected/aim.jsp">Назад</a>
	
	<br><h3><c:out value="История твоей целеустремлённости"></c:out></h3>
		
	<c:choose>
		<c:when test="${sessionScope.archiveAimData ne null}">
			<c:import url="../aimsFromArchiveTable.jsp"></c:import>
		</c:when>
		<c:otherwise><br><h3>Здесь будет ваша история, если конечно будет</h3></c:otherwise>
	</c:choose>
</body>
</html>