<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>성공</title>

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
작업이 완료되었습니다. 
<a href="sensorList.do">[리스트 보기]</a>

<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>

</body>
</html>