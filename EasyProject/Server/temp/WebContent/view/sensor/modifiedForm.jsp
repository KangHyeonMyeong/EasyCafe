<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>센서수정화면</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			센서수정
		</div>
<form action="sensorModified.do" method="POST">
<table>

<tr>
		<td>센서아이디</td>
		<td><input type="text" name="modi_id" value="${param.cur_id }"></td>
</tr>
<tr>
	<td>구분</td>
	<td>
	<select name="name" >
	<option value="Garbege">쓰레기통</option>
	<option value="Tissue">휴지</option>	
	<option value="Temp">온도</option>
	<option value="Humidity">습도</option>
	<option value="Seat">좌석</option>
	</select>
	</td>
</tr>

<tr>
	<td>알림설정값</td>
	<td><input type="text" name="setting"></td>
</tr>
<tr>
	<td><input type="submit" name="action" value="modify"></td>
	<td><input type="submit" name="action" value="delete"></td>
</tr>
<input type="hidden" name="cur_id" value="${param.cur_id }">
</table>
</form>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>