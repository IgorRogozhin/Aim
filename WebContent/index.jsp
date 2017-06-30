<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	<c:import url="motivation.jsp"></c:import>

	<!-- In case if user has already logged -->
	<c:if test="${sessionScope.authorized_user ne null}">
		<c:redirect url="/Protected/initializeSessionData.do" />
	</c:if>

	<c:if test="${requestScope.invalidName ne null}">
		<h2 class="attention">Похоже, ошибка в имени или пароле!</h2>
	</c:if>

	<c:if test="${sessionScope.wrongName ne null}">
		<h3 class="attention">Увы, но имя ${sessionScope.wrongName} уже
			занято, давай ещё!!</h3>
	</c:if>

	<div align="center" style="margin-top: 10%">
		<div id="static-tabbed-panel-div" style="width: 600px">
			<fieldset>
				<ul>
					<li><a href="#panel1">Войти</a></li>
					<li><a href="#panel2">Зарегистрироваться</a></li>
				</ul>
				<div id="panel1">
					<form id="login" method="post"
						onsubmit="return RegUtils.checkLoginCorrectness()"
						action="${pageContext.request.contextPath}/loginUser.do">
						<table style="width: 450px;">
							<tr>
								<td><span>Логин:</span></td>
								<td><input name="uid" id="loginString" type="text" required
									style="width: 250px;" value="${cookie.credentials_uid.value}" />
								</td>
							</tr>
							<tr>
								<td><span>Пароль:</span></td>
								<td><input name="pwd" id="loginPwd" type="password"
									required style="width: 250px;"
									value="${cookie.credentials_pwd.value}" /></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input name="rememberMe"
									type="checkbox" value="ON">&nbsp;Запомнить меня</td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="submit"
									id="buttonIn" value="Войти" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div id="panel2">
					<form id="registration" method="post"
						onsubmit="return RegUtils.checkCorrectness()"
						action="${pageContext.request.contextPath}/registerUser.do">
						<table style="width: 450px;">
							<tr>
								<td><span>Логин:</span></td>
								<td><input name="uid" type="text" id="loginField2" required
									style="width: 250px;" value="${cookie.credentials_uid.value}" />
								</td>
							</tr>
							<tr>
								<td><span>Пароль:</span></td>
								<td><input name="pwd" type="password" id="pwd" required
									style="width: 250px;" value="${cookie.credentials_pwd.value}" />
								</td>
							</tr>
							<tr>
								<td><span>Повторите&nbspпароль:</span></td>
								<td><input name="pwd2" type="password" id="pwd2" required
									style="width: 250px;" /></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input name="rememberMe"
									type="checkbox" value="ON">&nbsp;Запомнить меня</td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="submit"
									id="registrationButton" value="Записаться" /></td>
							</tr>
						</table>
					</form>
				</div>
			</fieldset>
			<div id="emptyFields">
				<span>Незаполненные поля!</span>
			</div>
			<div id="wrongRepeatedPwd">
				<span>Неверно повторен пароль!</span>
			</div>
		</div>
	</div>

</body>
</html>