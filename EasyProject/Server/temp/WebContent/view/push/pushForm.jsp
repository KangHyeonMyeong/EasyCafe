<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>푸시메시지</title>
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
			Push Message   
		</div>
		
<c:if test="${authUser.manager_flag == 1 }">
<form action="pushMessage.do" method="post">
<textarea name="Message" rows="10" cols="30">
</textarea><br>
<select name="main">
	<option value="쓰레기">쓰레기</option>
	<option value="온도">온도</option>
	<option value="티슈">휴지</option>
	
</select>
<input type="submit" value="send">
</form>
</c:if>
<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
</c:if>


</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>