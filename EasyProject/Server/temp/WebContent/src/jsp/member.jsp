<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>회원관리</title>

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
			회원관리
		</div>
		
		<div class="content">
			content
			<table border=1>
				<tr>
					<td>이름</td>
					<td>이메일</td>
					<td>생일</td>
					<td>전화번호</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>이메일</td>
					<td>생일</td>
					<td>전화번호</td>
				</tr>
			</table>
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>