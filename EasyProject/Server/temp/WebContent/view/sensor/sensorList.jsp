<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>sensor</title>
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
    background-color: #d2d2d2;
}

</style>
<!-- layout -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
		<div class="menuName">
			센서리스트
		</div>
<body>
<form action="sensorRegist.do" method="GET">
	<input type="submit" value="센서등록">
</form>
<a href="sensorList.do?category=Garbege">[쓰레기통]</a>
<a href="sensorList.do?category=Temp">[온도센서]</a>
<a href="sensorList.do?category=Humidity">[습도센서]</a>
<a href="sensorList.do?category=Tissue">[티슈센서]</a>
<a href="sensorList.do?category=Seat">[좌석센서]</a>
<a href="sensorList.do">[전체보기]</a>
<% int a = 0; %>
<table>
	<tr>
		<th>No.</th>
		<th>구분</th>
		<th>센서 ID</th>
		<th>현재 상태</th>
		<th>센서 등록날짜</th>
	</tr>
<c:if test="${sensorPage.getSensorList().isEmpty() }">등록된 센서가 없습니다.</c:if>
<c:forEach var="list" items="${ sensorPage.sensorList }">
<tr>
<%
	a= a + 1;
%>
	<td><%= a %></td>
	<td>${list.category }</td>
	<td><a href="sensorModified.do?cur_id=${list.sensor_id }">${list.sensor_id }</a></td>
	<td>${list.sensor_value }</td>
	<td>${list.sensor_date }</td>
</tr>
</c:forEach>
</table>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>

</body>
</html>