<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>성공이닷~!</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			Success Modifiy
		</div>

게시글을 수정했습니다~! <br>

<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=coffee">[커피메인]</a>
<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=ade">[에이드메인]</a>
<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=juice">[주스메인]</a>
<a href="${pageContext.request.contextPath}/menuRead.do?menu_name=${menu_name}">[내용 보기]</a>
</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>