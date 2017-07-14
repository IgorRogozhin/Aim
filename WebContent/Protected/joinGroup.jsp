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
	<c:import url="../navbar.jsp"></c:import>
	<h3 id="nameOfTable">
		<c:out value="Присоединяйся к группе"></c:out>
	</h3>
	<c:if test="${requestScope.alreadyIn ne null}">
		<h2 class="attention">Вы уже в группе
			"${requestScope.alreadyIn}", дважды в одну реку не войти</h2>
	</c:if>
	<c:if test="${requestScope.wrongInput ne null}">
		<h2 class="attention">У вас очепятка в
			"${requestScope.wrongInput}", наверное, вы хотели чего-то другого</h2>
	</c:if>

	<form action="${initParam.baseURL}/Protected/joinGroup.do"
		method="post">

		<table border=1 id="joinGroupTable" class="commonStyleOfTables">

			<tr>
				<td>Введите имя группы</td>
				<td><input type="text" required placeholder="Обязательное поле"
					name="nameOfGroup" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td><span>Пароль для группы:</span></td>
				<td><input name="pwd" required placeholder="Обязательное поле"
					type="password" style="width: 250px;" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="userId"
					value="${sessionScope.authorized_user.id}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit"
					name="addcity" value="Присоединиться" class="button" /></td>
			</tr>
		</table>
	</form>
</body>
</html>