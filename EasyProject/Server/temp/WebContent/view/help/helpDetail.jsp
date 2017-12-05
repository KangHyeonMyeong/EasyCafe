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
			<table border="1">
	<tr>
		<td>제목</td>
		<td colspan="2">${help.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="2">${help.content }</td>
	</tr>
	<tr>
		<td><a href="helpModifyForm.do?num=${help.num}&pageNum=${pageNum}">수정</a>
			<input type="hidden" name="pageNum" value="${pageNum}">
		</td>
		
		<td>
			<c:if test="${authUser.manager_flag == 1 }">
			<a href="helpDelete.do?num=${help.num}&pageNum=${currentPage}">삭제</a>
			</c:if>
			
			<c:if test="${auther.manager_flag == 2 || empty authUser }">
			권한이 없습니다.
			</c:if>
		</td>
	</tr>

	<tr>
		<td><a href="helpList.do?pageNum=${pageNum }">목록으로</a></td>
	</tr>
</table>
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>