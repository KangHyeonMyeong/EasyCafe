<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>헤더</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}\src\css\header.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}\src\css\dropDownMenu.css">
</head>
<body>
<div id="header">
		
		<!-- 로고 -->
		<div id="logo"><a href="${pageContext.request.contextPath}/home.do"><img src="${pageContext.request.contextPath}\img\logo.jpeg" alt="coffee" style="width: 100%; height: 100%"></a></div>
		
		<!-- 메뉴 -->
		<div id="menu">
			<ul class="navTop">
			<c:if test="${! empty authUser }">
				<c:if test="${ authUser.manager_flag == 1 }">
					<li><a href="#">${authUser.name }</a></li>
					<li>|</li>
					<li><a href="${pageContext.request.contextPath}/memberList.do">회원관리</a></li>
					<li>|</li>
					<li><a href="${pageContext.request.contextPath}/sensorList.do">센서설정</a></li>
					<li>|</li>
					<li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
				</c:if>
				
				<c:if test="${ authUser.manager_flag == 2 }">
					<li><a href="#">${authUser.name }</a></li>
					<li>|</li>
					<li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
				</c:if>
				
			</c:if>
			
			<c:if test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath}/login.do">로그인</a></li>
			</c:if>
			</ul>
			<ul class="nav">
		<li>
			<a href="${pageContext.request.contextPath}/menuPage.do?menu_category=coffee">메뉴</a>
			<div>
				<div class="nav-column">
					<h3><a href="${pageContext.request.contextPath}/menuPage.do?menu_category=coffee">커피</a></h3>
				</div>
				<div class="nav-column">
					<h3><a href="${pageContext.request.contextPath}/menuPage.do?menu_category=ade">에이드</a></h3>
					</div>
					<div class="nav-column">
					<h3><a href="${pageContext.request.contextPath}/menuPage.do?menu_category=juice">쥬스</a></h3>
				</div>
			</div>
		</li>
		<li><a href="${pageContext.request.contextPath}/cafestatusMain.do">매장 정보</a></li>
		<li>
			<a href="${pageContext.request.contextPath}/noticeList.do">뉴스</a>
			<div>
				<div class="nav-column">
					<h3><a href="${pageContext.request.contextPath}/noticeList.do">공지</a></h3>
				</div>

				<div class="nav-column">
					<h3><a href="${pageContext.request.contextPath}/helpList.do">도움말</a></h3>
				</div>

			
			</div>
		</li>
		<li><a href="${pageContext.request.contextPath}/orderList.do">주문정보</a></li>
	</ul>
		
		</div>
	</div>
</body>
</html>