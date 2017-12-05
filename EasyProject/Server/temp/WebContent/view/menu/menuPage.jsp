<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<style type='text/css'>
table{
	
	width: 1000px;
	height: 400px;
	padding: 10px;
	display: inline;
}

</style>
<body>
<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container" >
		<div class="menuName">
			Menu
		</div>


<h1>${param.menu_category}</h1>
<div id="slidebox">
<c:forEach var="List" items="${menuList}">
<table>
	<tr>
	<td><a href="menuRead.do?menu_name=${List.menu_name }">
	<img alt="이미지 경로 못찾음" src="${pageContext.request.contextPath}/upload/${List.menu_image}" width="300px" height="200px"></a></td>
	</tr>
	<tr>
	<td align=center>${List.menu_name}</td>
	
	<tr>
</table>
</c:forEach>
</div>
<br>

<a href="menuRegister.do" ><input type="submit" value="메뉴 등록하기"></a>

</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>