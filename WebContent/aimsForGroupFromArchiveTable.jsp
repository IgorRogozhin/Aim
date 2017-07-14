<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css" />
<link rel="stylesheet" href="css/styles.css" />
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ui.js"></script>
<script src="scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.userAimGroupData ne null}">
			<div class="commonStyleOfTables">
				<table id="groupArchiveTable">
					<thead id="headerTest" style="text-align: left">
						<tr>
							<td>Группа</td>
							<td>Кто поставил</td>
							<td>Название задачи</td>
							<td>Описание</td>
							<td>Проверка</td>
							<td>Дата окончания</td>
							<td>Итог</td>
						</tr>
					</thead>
					<c:forEach var="data" items="${sessionScope.userAimGroupData}">
						<!-- Iterating over data for current WebUser to retrieve actual aims-->
						<c:forEach var="group" items="${data}">
							<c:if test="${group.webAim.archive}">
								<tr>
									<td>${group.webGroup.name}</td>
									<td>${group.webUser.userId}</td>
									<td>${group.webAim.name}</td>
									<td>${group.webAim.description}</td>
									<td>${group.webAim.control}</td>
									<td>${group.webAim.deadline}</td>
									<td><c:choose>
											<c:when test="${group.webAim.result}">Выполнено</c:when>
											<c:otherwise>Не получилось</c:otherwise>
										</c:choose></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</c:when>
		<c:otherwise>У вас нет групп, для получения данных</c:otherwise>
	</c:choose>
</body>
</html>