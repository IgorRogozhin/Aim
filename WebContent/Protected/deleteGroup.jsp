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
	<c:import url="../navbar.jsp"></c:import>

	<form action="${initParam.baseURL}/Protected/deleteGroup.do" method="post">
	
				<table border=1> 
				
					<tr>
						<td>Удалить группу</td>
						<td>
							<c:choose>
								<c:when test="${sessionScope.groupData ne null}">
									 <c:forEach var="group" items="${sessionScope.groupData}">
									 	<c:choose>
									 		<c:when test="${group.creator}">
									 			<ul>
								 					<li><input type="checkbox" name="groups[]" value="${group.name}" />${group.name}</li>
								 				</ul>
								 			</c:when>
								 			<c:otherwise>
								 				<b>"${group.name}"</b> не вами создана, увы
								 			</c:otherwise>
								 		</c:choose>
								 	 </c:forEach>
								</c:when>
							<c:otherwise>
									У вас пока нет групп
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" name="addAim" value="Удалить группу" style="width:200px;"/>
						</td>
					</tr>
				</table>
			</form>
</body>
</html>