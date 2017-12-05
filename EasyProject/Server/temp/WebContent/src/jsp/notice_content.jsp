<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>내용</title>

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
		<div class="menuName">
			${articleData.article.title }
		</div>
		
		<div class="content">
		<table border="1" width=100%>
			<tr>
				<td>번호</td>
				<td>${articleData.article.num }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${articleData.article.writer.e_mail}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${articleData.article.title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${articleData.content }</td>
			</tr>
		</table>
		
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>