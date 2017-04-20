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
	<a href="${pageContext.request.contextPath}/Protected/group.jsp">Назад</a>
	
	<br><h3><c:out value="История целеустремлённости в твоих группах"></c:out></h3>
		
	<c:choose>
		<c:when test="${sessionScope.userAimGroupData ne null}">
			<c:import url="../aimsForGroupFromArchiveTable.jsp"></c:import>
		</c:when>
		<c:otherwise><br><h3>Здесь будет история групп в которые ты входишь, если конечно будет</h3></c:otherwise>
	</c:choose>
</body>
</html>