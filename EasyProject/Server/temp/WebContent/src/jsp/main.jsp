<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>레이아웃</title>

<!-- layout -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_main.css">
<!-- banner -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\simpleBanner.css">
<!-- notice -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\notice_main.css">

<!-- banner -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}\src\js\simpleBanner.js"></script>
<!-- map -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAs39pncI2HF8xHOZBXmMNAPcCRYaMHw0k&callback=initMap" async defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}\src\js\map.js"></script>

</head>
<body>
<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>
<div id="frame">
	<!-- banner -->
	<div class="simple_banner_wrap">
		<ul>
			<li style="background-color:#9f6;"><a href="#" style="text-decoration:none"><img src="\temp\img\img01.jpg"></a></li>
			<li style="background-color:#69f;"><a href="#" style="text-decoration:none"><img src="\temp\img\img02.jpg"></a></li>
			<li style="background-color:#9f6;"><a href="#" style="text-decoration:none"><img src="\temp\img\img03.jpg"></a></li>
		</ul>
	</div>
	
	<!-- container -->
	<div id="container">
		<div id="notice">
		<table id="notice_main" width=100%>
			<c:forEach items="${list }" var="list">
				<tr>
					<td><td><a href="noticeDetail.do?num=${list.num}&pageNum=${pageNum}">${list.title }</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		<div id="location">
			<div id="map"></div>
			
		</div>
	</div>
	
	<!-- footer -->
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>