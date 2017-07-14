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
		<!-- context is good -->
		<ul class="zeroStyle">
			<li id="addLink" class="link"><a href="${initParam.baseURL}/Protected/addGroup.jsp">Создать группу</a></li>
			<li id="joinLink" class="link"><a href="${initParam.baseURL}/Protected/joinGroup.jsp">Присоединиться</a></li>
			<li id="signoutLink" class="link"><a href="${initParam.baseURL}/Protected/signoutGroup.jsp">Выйти из группы</a></li>
			<li id="archiveLink" class="link"><a href="${initParam.baseURL}/Protected/archiveGroup.jsp">Архив групп</a></li>
			<li id="deleteLink" class="link"><a href="${initParam.baseURL}/Protected/deleteGroup.jsp">Удалить группы</a></li>
			<li id="rateLink" class="link"><a href="${initParam.baseURL}/rateTable.jsp">Рейтинг</a></li>
			<li id="backLink" class="link"><a href="${pageContext.request.contextPath}/Protected/Cabinet.jsp">Кабинет</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<!-- use base links -->
		<ul class="zeroStyle">
			<li id="addLink" class="link"><a href="${pageContext.request.contextPath}/Protected/addGroup.jsp">Создать группу</a></li>
			<li id="joinLink" class="link"><a href="${pageContext.request.contextPath}/Protected/joinGroup.jsp">Присоединиться</a></li>
			<li id="signoutLink" class="link"><a href="${pageContext.request.contextPath}/Protected/signoutGroup.jsp">Выйти из групп</a></li>
			<li id="archiveLink" class="link"><a href="${pageContext.request.contextPath}/Protected/archiveGroup.jsp">Архив группы</a></li>
			<li id="deleteLink" class="link"><a href="${pageContext.request.contextPath}/Protected/deleteGroup.jsp">Удалить группы</a></li>
			<li id="rateLink" class="link"><a href="${pageContext.request.contextPath}/rateTable.jsp">Рейтинг</a></li>
			<li id="backLink" class="link"><a href="${pageContext.request.contextPath}/Protected/Cabinet.jsp">Кабинет</a></li>
		</ul>
	</c:otherwise>
</c:choose>	
<br>		
</body>
</html>