<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 리스트</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<style>
td{
	width:100px;
}
</style>
</head>

<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			Order List
		</div>


<c:if test="${authUser.manager_flag == 1 }">

<table border="1" width="100%">

	
	<tr align=center>
	<td>주문날짜</td>
	<td>주문자 아이디</td>
	<td>주문자 이름</td>
	<td>주문 내용</td>
	<td>주문 상태</td>
	
	</tr>

<c:if test="${orderPage.getOrderList().isEmpty() }">주문내역이 없습니다</c:if>
<c:forEach var="list" items="${orderPage.orderList}">
	
	<tr align=center>
	<td>${list.order_date}</td>
	<td>${list.order_id}</td>
	<td>${list.order_name}</td>
	<td><pre>${list.order_content}</pre></td>
	<td>
	<form method="post" action="${pageContext.request.contextPath}/orderStatus.do">
	<input type="hidden" name="order_status" value="${list.order_status }">
	<input type="hidden" name="order_date" value="${list.order_date }">
	<input type="hidden" name="order_id" value="${list.order_id }">
	<input type="submit" value="${list.order_status }">
	</form>
	</td>
	
	</tr>
</c:forEach>

</table>

</c:if>
<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
</c:if>
</div>

</div>
</body>
</html>