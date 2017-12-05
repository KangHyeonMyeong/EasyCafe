<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title>도움말</title>

<!-- layout -->
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container">
		<div class="menuName">
			공지
		</div>
		
	<div class="content">
	<c:if test="${authUser.manager_flag == 1 }">
		<form action="noticeWrite.do" method="post">
		
<p>
   <label class="w3-text-black"><b>제목</b></label>
   <input class="w3-input w3-border" type="text" name="title" value="${notice.title}">
</p>
		
<p>
  <label class="w3-text-black"><b>내용</b></label>
   <textarea class="w3-input w3-border"  name="content" rows="5" cols="30">${notice.content}</textarea>
</p>


<input class="w3-btn w3-black" type="submit" value="공지등록">
		
		

		</form>
	</c:if>
		
	<c:if test="${auther.manager_flag == 2 || empty authUser }">
		권한이 없습니다.
	</c:if>
		
		</div>
	</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>