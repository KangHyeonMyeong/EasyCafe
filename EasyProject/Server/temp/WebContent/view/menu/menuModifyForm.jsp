<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메뉴수정</title>
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
			Menu 수정
		</div>


<c:if test="${authUser.manager_flag == 1 }">

<form name="form" action="menuModify.do" method="POST" enctype="multipart/form-data">

<p>
	<label class="w3-text-black">메뉴 카테고리</label>
   <select class="w3-select" id="menu_category" name="menu_category">
   <option value="choice" selected>분류선택</option>
   <option value="coffee">커피</option>
   <option value="ade">에이드</option>
   <option value="juice">주스</option>
   </select>
</p>

       <p>
   <label class="w3-text-black"><b>메뉴이름</b></label>
   <input class="w3-input w3-border" type="text" name="menu_name" value="${param.menu_name}">
</p>

<p>
   <label class="w3-text-black"><b>Hot/Small가격</b></label>
   <input class="w3-input w3-border" type="number" name="HSprice" value="${menuData.menu.price_HS}">
</p>

<p>
   <label class="w3-text-black"><b>Hot/Large가격</b></label>
   <input class="w3-input w3-border" type="number" name="HLprice" value="${menuData.menu.price_HL}">
</p>

<p>
   <label class="w3-text-black"><b>Ice/Small가격</b></label>
   <input class="w3-input w3-border" type="number" name="ISprice" value="${menuData.menu.price_IS}">
</p>
   
   <label class="w3-text-black"><b>Ice/Large가격</b></label>
   <input class="w3-input w3-border" type="number" name="ILprice" value="${menuData.menu.price_IL}">

<p>
  <label class="w3-text-black"><b>메뉴설명</b></label>
   <textarea class="w3-input w3-border" id="menu_info" name="menu_info" rows="5" cols="30">${menuData.menu.menu_info}</textarea>
</p>

<p>
   <label class="w3-text-black"><b>메뉴 이미지</b></label>
   <input class="w3-btn w3-black" type="file" id="uploadfile" name="uploadfile">${menuData.menu.menu_image}
</p>

<input class="w3-btn w3-black" type="submit" value="메뉴수정">
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

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
$( document ).ready(function() {
	
	var menu_category = "${menuData.menu.menu_category}";
	
	$("#menu_category").val(menu_category).attr("selected", "selected");
});

</script>

</html>