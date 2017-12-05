<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>loginForm</title>
<!-- layout -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<div id="frame">
	<!-- container -->
	<div id="container">
		<form action="<%= request.getContextPath() %>/login.do" method="post">
			<c:if test="${errors.idOrPwNotMatch }">
			아이디와 암호가 일치하지 않습니다.
			</c:if>
			<p>
				<input type ="text" name="e_mail" value="${param.e_mail }">
				<c:if test="${errors.id }">아이디를 입력하세요</c:if>
			</p>
			<p>
				<input type="password" name="password">
				<c:if test="${errors.password }">암호를 입력하세요</c:if>
			</p>
			<input type="submit" value="로그인">
		</form>
	</div>
	
	<!-- footer -->
	<div id="footer">&copy;copy</div>
		
</div>

</body>
</html>