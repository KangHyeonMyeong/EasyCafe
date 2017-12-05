<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title></title>
</head>
<body>
<table border="1">
	<tr>	
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성시간</td>				
	</tr>
	
	<c:if test="${noticePage.hasNoNotice }">
		<tr>
			<td colspan="4">게시글이 없습니다.</td>
		</tr>
	</c:if>
	
	<c:forEach var="notice" items="${noticePage.content}">
		<tr>
			<td>${notice.num }</td>
			<td><a href="#"><c:out value="${notice.title }"/></a></td>
			<td>${notice.e_mail }</td>
			<td>${notice.notice_time }</td>
		</tr>
	</c:forEach>
	
	<c:if test="${noticePage.hasNotice }">
		<tr>
			<td colspan="4">
			<c:if test="${noticePage.startPage > 5 }">
			<a href="#">[이전]</a>
			</c:if>
		</tr>
	</c:if>
	
</table>
</body>
</html>