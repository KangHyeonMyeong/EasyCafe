<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>메뉴 목록</title>
<style>
td{
	width:100px;
}

</style>
</head>
<body>

<table border="1" width="100%">
	<tr>
	<td colspan="7">
	<a href="menuRegister.do">[메뉴등록]</a>
	<a href="menuList.do">[전체보기]</a>
	<a href="menuList.do?menu_category=coffee">[커피]</a>
	<a href="menuList.do?menu_category=ade">[에이드]</a>
	<a href="menuList.do?menu_category=juice">[주스]</a>
	</td>
	</tr>
	
	<tr align=center>
	<td>분류선택</td>
	<td>메뉴아이디</td>
	<td>메뉴이름</td>
	<td>가격분류</td>
	<td>메뉴가격</td>
	<td>메뉴설명</td>
	<td>메뉴이미지</td>
	</tr>


<c:if test="${menuPage.getMenuList().isEmpty() }">등록된 메뉴가 없습니다</c:if>
<c:forEach var="list" items="${menuPage.menuList}">
	
	<tr align=center>
	<td>${list.menu_category}</td>
	<td style="cursor: pointer"><a href="menuRead.do?menu_id=${list.menu_id}">
		<c:out value="${list.menu_id}"/></a></td>
	<td>${list.menu_name}</td>
	<td>${list.price_category}</td>
	<td>${list.price}</td>
	<td>${list.menu_info}</td>
	<td>${list.menu_image}</td>
	</tr>
</c:forEach>

</table>
</body>
</html>