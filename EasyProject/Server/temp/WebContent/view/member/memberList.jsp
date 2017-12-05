<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>회원리스트</title>

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
			Member List
		</div>
		
		<div class="content">
			<c:if test="${count == 0}">
	<table border="1">
		<tr>
			<td align="center">회원이 없습니다.</td>
		</tr>
	</table>
</c:if>

<c:if test="${count > 0}">
	<table border="1" width="100%">
		<tr align = center >
			<td>no</td>
			<td>e_mail</td>
			<td>비밀번호</td>
			<td>회원이름</td>
			<td>생년월일</td>
			<td>전화번호</td>
			<td>관리자 구분</td>
		</tr>
		
		<c:forEach var="member" items="${memberList}">
		<tr align = center >
			<td>${member.num }</td>
			<td><a href="memberDetail.do?num=${member.num}&pageNum=${currentPage}">${member.e_mail }</a></td>
			<td>${member.password }</td>
			<td>${member.name }</td>
			<td>${member.birth }</td>
			<td>${member.phone }</td>
			<td>${member.manager_flag }</td>
		</tr>
		</c:forEach>
	</table>
</c:if>


<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
	<c:set var="pageBlock" value="${10}"/>
	<c:set var="startPage" value="${1}"/>
	<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true"/>
	<c:if test="${currentPage % 10 != 0}">
		<c:set var="startPage" value="${result * 10 + 1}"/>
	</c:if>
	<c:if test="${currentPage % 10 == 0}">
		<c:set var="startPage" value="${(result -1) * 10 + 1}"/>
	</c:if>
	<c:set var="endPage" value="${startPage +pageBlock -1}"/>
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	
	
	<c:if test="${startPage > 10}">
		<a href="memberList.do?pageNum=${startPage - 10}">[이전]</a>
	</c:if>
	
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="memberList.do?pageNum=${i}">[${i}]</a>
	</c:forEach>

	
	<c:if test="${endPage < pageCount}">
		<a href="memberList.do?pageNum=${startPage + 10}">[다음]</a>
	</c:if>
</c:if>
		</div>

</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>