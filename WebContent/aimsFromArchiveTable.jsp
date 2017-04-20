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

	<br><table border=1> 
				<tr>
					<td>Название задачи</td>
					<td>Описание</td>
					<td>Проверка</td>
					<td>Дата окончания</td>
					<td>Итог</td>
				</tr>
				
				<!-- Iterating over all WebAims for current WebUser -->
				<c:forEach var="aim" items="${sessionScope.archiveAimData}">
					<tr>
						<td>${aim.name}</td>
						<td>${aim.description}</td>
						<td>${aim.control}</td>
						<td>${aim.deadline}</td>
						<td>
							<c:choose>
								<c:when test="${aim.result}">Выполнено</c:when>
								<c:otherwise>Не выполнено</c:otherwise>
							</c:choose>	
						</td>
												
					</tr>
				</c:forEach>
		</table>
</body>
</html>