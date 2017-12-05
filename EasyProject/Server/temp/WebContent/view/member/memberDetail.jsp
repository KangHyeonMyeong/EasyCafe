<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>회원정보</title>

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
			회원 상세정보
		</div>
		
		<div class="content" >
			<table border="1" >
	<tr>
		<td align="center" >권한</td>
		<td colspan="2">${member.manager_flag }</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="2">${member.name }</td>
	</tr>
	<tr>
		<td align="center">아이디</td>
		<td colspan="2">${member.e_mail }</td>
	</tr>
	<tr>
		<td align="center">생일</td>
		<td colspan="2">${member.birth }</td>
	</tr>
	<tr>
		<td align="center">전화번호</td>
		<td colspan="2">${member.phone }</td>
	</tr>
	<tr>
		<td><input type="button" value="권한부여" onclick="document.location.href='memberFlagGrant.do?num=${num}&pageNum=${pageNum}'"></td>
		<td><input type="button" value="권한취소" onclick="document.location.href='memberFlagDelete.do?num=${num}&pageNum=${pageNum}'"></td>
		<td><input type="button" value="회원탈퇴" onclick="document.location.href='memberDelete.do?num=${num}&pageNum=${pageNum}'"></td>
		<!-- <td><a href="memberDelete.do?num=${num}&pageNum=${pageNum}">회원탈퇴</a></td> -->
	</tr>
	<tr>
		<td><input type="button" value="회원목록" onclick="document.location.href='memberList.do?pageNum=${pageNum}'"></td>
	</tr>
</table>
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>