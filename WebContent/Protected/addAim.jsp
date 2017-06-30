<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:import url="../navbarAim.jsp"></c:import>
	<h3 id="nameOfTable"><c:out value="Сформулируй свою задачу"></c:out></h3>
	<c:if test="${sessionScope.occupiedAimName ne null}">
		<h2 class="attention">Задача "${sessionScope.occupiedAimName}"
			уже поставлена, смените название</h2>
	</c:if>

	<form action="${initParam.baseURL}/Protected/addNewAim.do"
		method="post">

		<table border=1 id="addAimTable"  class="commonStyleOfTables">

			<tr>
				<td>Введите имя задачи</td>
				<td><input type="text" required placeholder="Обязательное поле"
					name="nameOfAim" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>Опишите задачу</td>
				<td><textarea required placeholder="Обязательное поле"
						rows="10" cols="45" name="description" style="width: 100%;"></textarea></td>
			</tr>
			<tr>
				<td>Дата выполнения</td>
				<td><input id="start-date" name="calendar" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>Критерий проверки</td>
				<td><textarea rows="10" cols="45" name="control"
						style="width: 100%;"></textarea></td>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="userId"
					value="${sessionScope.authorized_user.id}" /></td>
			</tr>
			<tr>
				<td>Подключить группу</td>
				<td><c:choose>
						<c:when test="${sessionScope.groupData ne null}">
							<c:forEach var="group" items="${sessionScope.groupData}">
								<ul>
									<li><input type="checkbox" name="groups[]"
										value="${group.id}" />${group.name}</li>
								</ul>
							</c:forEach>
						</c:when>
						<c:otherwise>
									у вас пока нет групп
							</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit"
					name="addAim" value="Поставить задачу" class="button" /></td>
			</tr>
		</table>
	</form>
</body>
</html>