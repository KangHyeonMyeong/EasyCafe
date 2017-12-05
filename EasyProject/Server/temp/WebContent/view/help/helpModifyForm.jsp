<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>도움말</title>

<!-- layout -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
</head>
<body>
<% request.setCharacterEncoding("utf-8");%>
<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			도움말
		</div>
		
		<div class="content">
		<c:if test="${authUser.manager_flag == 1 }">	
		<form method="POST" action="helpModify.do">
		<table >
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${help.title }">
				<input type="hidden" name="num" value="${help.num}">
				<input type="hidden" name="pageNum" value="${pageNum}">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content">${help.content }</textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정"></td>
			</tr>
		</table>
		</form>
		</c:if>
		
	<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
	</c:if>
		
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>