<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/jquery-ui/jquery-ui.css"/>
<link rel="stylesheet" href="../css/styles.css"/>
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<script src="../scripts/script.js"></script>
<title>Reach your aim</title>
</head>
<body>
	<c:import url="../motivation.jsp"></c:import>
	<c:import url="../navbarAim.jsp"></c:import>
	
	<br><h3 id="nameOfTable"><c:out value="Твои текущие задачи"></c:out></h3>
	
	<c:choose>
		<c:when test="${sessionScope.aimData ne null}">
			<c:import url="../aimsByDeadlineTable.jsp"></c:import>
		</c:when>
		<c:otherwise><br><h3 class="styleNameOfTable">Здесь будут ваши текущие задачи, если вы их перед собой поставите</h3></c:otherwise>
	</c:choose>
	
</body>
</html>