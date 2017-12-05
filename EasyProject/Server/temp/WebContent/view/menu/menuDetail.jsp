<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세정보</title>
<link type="text/css" rel="stylesheet" href="\temp\src\css\layout_sub.css">
</head>
<body>

<header>
	<jsp:include page="/src/jsp/header.jsp"></jsp:include>
</header>

<div id="frame">
	<!-- container -->
	<div id="container" >
		<div class="menuName">
			Detail Information
		</div>



<table > 
	
   <tr> 
     <td rowspan="5"  width="200">
       <img src="${pageContext.request.contextPath}/upload/${menuData.menu.menu_image}" width="400px" height="300px"></td> 
     <td width="30"></td>
     <td width="700"></td>
   </tr> 
   <tr>
     <td width="30" height="50"></td>
   	 <td width="700" height="50" style="font-size:160%"><b>${menuData.menu.menu_name}</b>
   	</td>
    
   </tr>
    <tr>
     <td width="30"></td>
   	 <td width="700"></td>
   </tr>
   <tr>
  	 <td width="30"></td>
   	 <td width="700">${menuData.menu.menu_info}</td> 
   </tr>
   
   <tr>
     <td width="30" height="50"></td>
   	 <td width="700" height="50"></td>
   </tr>
 
</table>
<br><br>
<table style="border:1px solid #1E0000; border-radius: 3px;">
<tr>
<td colspan="3" style="text-align:center; border:1px solid #1E0000;" width=400px >메뉴 가격</td>
</tr>

<tr align="center">
<td width=70px style="border:1px solid #1E0000;"></td>
<td width=100px style="border:1px solid #1E0000;">HOT</td>
<td width=100px style="border:1px solid#1E0000;">ICE</td>
</tr>
<tr align="center">
<td style="border:1px solid #1E0000;" >small</td>
<td style="border:1px solid #1E0000;">${menuData.menu.price_HS}</td>
<td style="border:1px solid #1E0000;">${menuData.menu.price_IS}</td>
</tr>
<tr align="center">
<td style="border:1px solid #1E0000;">large</td>
<td style="border:1px solid #1E0000;">${menuData.menu.price_HL}</td>
<td style="border:1px solid #1E0000;">${menuData.menu.price_IL}</td>
</tr>

</table>




<br/><br/>

<a href="menuPage.do?menu_category=coffee" ><input type="submit" value="메인화면"></a>
<a href="menuModify.do?menu_name=${menuData.menu.menu_name}"><input type="submit" value="메뉴수정"></a>
<a href="menuDelete.do?menu_name=${menuData.menu.menu_name}" ><input type="submit" value="메뉴삭제"></a>

</div>

	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
</html>