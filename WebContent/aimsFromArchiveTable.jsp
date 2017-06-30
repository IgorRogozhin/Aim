<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css" />
<link rel="stylesheet" href="css/styles.css" />
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
	<div class="commonStyleOfTables">
		<table id="archiveTable">
			<thead id="headerTest" style="text-align: left">
				<tr>
					<td>Название задачи</td>
					<td>Описание</td>
					<td>Проверка</td>
					<td>Дата окончания</td>
					<td>Итог</td>
				</tr>
			</thead>
			<!-- Iterating over all WebAims for current WebUser -->
			<c:forEach var="aim" items="${sessionScope.archiveAimData}">
				<tr>
					<td>${aim.name}</td>
					<td>${aim.description}</td>
					<td>${aim.control}</td>
					<td>${aim.deadline}</td>
					<td><c:choose>
							<c:when test="${aim.result}">Выполнено</c:when>
							<c:otherwise>Не выполнено</c:otherwise>
						</c:choose></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>