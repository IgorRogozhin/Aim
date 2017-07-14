<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/jquery-ui/jquery-ui.css" />
<link rel="stylesheet" href="../css/styles.css" />
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<script src="../scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
	<c:import url="../motivation.jsp"></c:import>
	<c:import url="../navbar.jsp"></c:import>
	<h3 id="nameOfTable"><c:out value="Какае группы вы хотите покинуть?"></c:out></h3>
	<form action="${pageContext.request.contextPath}/Protected/signoutGroup.do" method="post">
			
		<c:choose>
			<c:when test="${sessionScope.groupData ne null}">
				<table border=1 id="signoutGroupTable"  class="commonStyleOfTables"> 
				<tr>
					<td>
						<br> Учтите, покинуть можно только группы к которым вы присоединялись.
						<br> Если вы создавали группу, то её можно только удалить.
					</td>
					<td>
					 	<c:forEach var="group" items="${sessionScope.groupData}">
							<c:if test="${not group.creator}">
								 <ul>
								 	<li><input type="checkbox" name="groups[]" value="${group.id}" />${group.name}</li>
								 </ul>
							</c:if>
						</c:forEach>
					</td>
				</tr>					
							 
				<tr>
					<td>
							<input type="hidden" name="userId" value="${sessionScope.authorized_user.id}" />
					</td>
					<td colspan="2">
						<input type="submit" name="addAim" value="Покинуть" id="signoutButton"/>
					</td>
				</tr>
				</table>	 	 
			</c:when>
			<c:otherwise>
				У вас пока нет групп, чтобы их покинуть
			</c:otherwise>
		</c:choose>
					
	</form>
</body>
</html>