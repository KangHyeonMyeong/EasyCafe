<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
<div id="container">
		<div class="menuName">
			테이블 수정
		</div>
<div class="w3-container w3-blue">
</div>

<c:if test="${authUser.manager_flag == 1 }">
<form id="upForm1" name="reg_table" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/tableModify.do">

<p>
	<label class="w3-text-black"><b>테이블 번호</b></label>
	<input class="w3-input w3-border" type="number" name="table_num">
</p>

<p>
	<label class="w3-text-black"><b>테이블 사진: </b></label>
	<input class="w3-input w3-border" type="file" name="filename">
</p>

<p>
	<label class="w3-text-black"><b> 콘센트:</b></label>
	<select class="w3-select" name="consent">
		<option value="Y">있음</option>
		<option value="N">없음</option>
	</select>
</p>

<p>
<label class="w3-text-black"><b> 담요:</b></label>
<select class="w3-select" name="blanket">
		<option value="Y">있음</option>
		<option value="N">없음</option>
</select>
	</p>
	
<p>
<label class="w3-text-black"><b> 쿠션:</b></label>
	<select class="w3-select" name="cushion">
		<option value="Y">있음</option>
		<option value="N">없음</option>
	</select>
	</p>
<input class="w3-btn w3-black" type="submit" value="테이블수정">

</form>

</c:if>
<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
</c:if>


<div id="upResult"></div>
</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>