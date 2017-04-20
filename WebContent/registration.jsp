<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Reach your aim</title>
</head>
<body>
	<c:import url="motivation.jsp"></c:import><br>
	<h2>Для начала зарегистрируйся!</h2> 
		
		<c:if test="${sessionScope.wrongName ne null}">
			<h3>Увы, но имя ${sessionScope.wrongName} уже занято, давай ещё!!</h3>
		</c:if>
			
	<form id="login" method="post" action="${pageContext.request.contextPath}/registerUser.do">
				<table style="width:450px;">
					<tr>
						<td>
							<span>Логин:</span>
						</td>
						<td>
							<input name="uid" type="text" style="width:250px;" value="${cookie.credentials_uid.value}" />
						</td>
					</tr>
					<tr>
						<td>
							<span>Пароль:</span>
						</td>
						<td>
							<input name="pwd" type="password" style="width:250px;" value="${cookie.credentials_pwd.value}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input name="rememberMe" type="checkbox" value="ON">&nbsp;Запомнить меня
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="Записаться" 
							    style="width:250px;" />
						</td>
					</tr>
					
				</table>
				
			</form>
			
</body>
</html>