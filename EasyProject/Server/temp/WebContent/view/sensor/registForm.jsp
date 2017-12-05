<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>센서수정화면</title>
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
			센서등록
		</div>
<form action="sensorRegist.do" method="POST">
<p>
	<label class="w3-text-black"><b>센서아이디</b></label>
	<input class="w3-input w3-border" type="text" name="sensor_id">
</p>
<p>
	<label class="w3-text-black"><b>센서분류</b></label>
	<select class="w3-select" name="name" onchange="category(this.form)">
	<option>분류선택</option>
	<option value="Garbege">쓰레기통</option>
	<option value="Tissue">휴지</option>	
	<option value="Temp">온도</option>
	<option value="Humidity">습도</option>
	<option value="Seat">좌석</option>
</select>
</p>
<p>
	<label class="w3-text-black"><b>알람설정</b></label>
	<input class="w3-input w3-border" type="text" name="setting">
</p>

<p>
  <input class="w3-input w3-border" type="submit" value="등록"> 
</p>
</form>
 </div>
	<!-- footer --> 
	<div id="footer">&copy;copy</div>
	
</div>
</body>
<script type="text/javascript">
function category(frm) {

    var sensor_id = frm.name.selectedIndex;

    switch(sensor_id){
       case 1:
        frm.sensor_id.value = 'GAR';
       break;
      case 2:
        frm.sensor_id.value = 'TI';
       break;
      case 3:
        frm.sensor_id.value = 'TEM';
       break;
      case 4:
          frm.sensor_id.value = 'HUM';
         break;  
      case 5:
          frm.sensor_id.value = 's';
         break;  
    }
    return true;
}
</script>
</html>