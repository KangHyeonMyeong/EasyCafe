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
			제목
		</div>
		
		<div class="content">
			<table border="1">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성시간</td>				
				</tr>
				<c:if test="${articlePage.hasNoArticles() }">
					<tr>
						<td colspan="4">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="article" items="${articlePage.content }">
					<tr>
						<td>${article.number }</td>
						<td><a href="#"></a>aset</td>
						<td>${article.writer.name }</td>
						<td>${article.readCount }</td>
					</tr>
				</c:forEach>
				<c:if test="${articlePage.hasArticles() }">
					<tr>
						<td colspan="4">
							<c:if test="${articlePage.startPage > 5 }">
								<a href="#">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
								<a href="#">[${pNo }]</a>
							</c:forEach>
							<c:if test="${articlePage.endPage < articlePage.startPage + 5 }">
								<a href="#">[다음]</a>
							</c:if>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>