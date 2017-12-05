<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>매장상태정보</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			매장정보
		</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/status.js"></script>

<b>좌석정보</b>(사용중/총좌석) : <a>${cafeInfo.current_seat }/${ cafeInfo.total_seat}</a><br>
<b>온도</b> : <a>${cafeInfo.temp_level }℃</a><br>
<b>습도</b> : <a>${cafeInfo.humidity_level }%</a><br>
<b>소음</b> : <a>${cafeInfo.noise_level }dB</a><br>
<br>

<a href="${pageContext.request.contextPath}/view/cafestatus/tablemodify.jsp"> [테이블 수정하기]</a><br><br>

<div class="result" style="background: white">loading...</div> 

테이블 클릭 시 상세정보를 확인 할 수 있습니다! 


</div>




	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>