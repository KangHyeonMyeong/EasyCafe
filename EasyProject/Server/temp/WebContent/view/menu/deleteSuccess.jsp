<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제성공</title>
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
			Success Delete
		</div>


<c:if test="${authUser.manager_flag == 1 }">


게시글을 삭제했습니다!!!!  <br>

<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=coffee">[커피메인]</a>
<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=ade">[에이드메인]</a>
<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=juice">[주스메인]</a>

</c:if>
<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
</c:if>

</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>

</body>
</html>
