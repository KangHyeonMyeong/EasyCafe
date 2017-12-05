<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>등록성공</title>
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
			Success Regist
		</div>

메뉴가 등록되었습니다 <br>
${ctxPath = pageContext.request.contextPath;''}
<a href="${ctxPath}/menuRegister.do">[메뉴등록]</a>
<a href="${ctxPath}/menuPage.do?menu_category=coffee">[커피메인]</a>
<a href="${ctxPath}/menuPage.do?menu_category=ade">[에이드메인]</a>
<a href="${ctxPath}/menuPage.do?menu_category=juice">[주스메인]</a>
<a href="${ctxPath}/menuRead.do?menu_name=${menu_name}">[내용 보기]</a>

</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>