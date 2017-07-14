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
		<span id="message"></span>
		<table id="deadlineTable">
			<thead id="headerTest">
				<tr>
					<td>Название задачи</td>
					<td>Описание</td>
					<td>Проверка</td>
					<td>Дата окончания</td>
					<td>Редактировать</td>
				</tr>
			</thead>
			<!-- Iterating over all WebAims for current WebUser -->
			<c:forEach var="aim" items="${sessionScope.aimData}">
				<tr class="passive">
					<td>${aim.name}</td>
					<td>${aim.description}</td>
					<td>${aim.control}</td>
					<td>${aim.deadline}</td>
					<td>
						<div class="toServer">
							<input type="hidden" id="id" name="userId" value="${aim.userId}">
							<input type="hidden" id="nameOfAim" value="${aim.name}">
							<input type="hidden" id="description" value="${aim.description}">

							<input type="button" id="deleteButt" class="button"
								value="Удалить"> <input type="button" id="doneButt"
								class="button" value="Сделано!"> <input type="button"
								id="failedButt" class="button" value="Не удалось">
						</div>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>