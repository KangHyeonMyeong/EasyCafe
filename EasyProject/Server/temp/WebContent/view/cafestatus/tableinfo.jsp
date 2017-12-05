<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="width=300, user-scalable=yes">
<script type="text/javascript">
function Popclose(){
	window.close();
	self.close();
	history.back();
}
</script>
<div align="center" style="width: 1000px; height: 800px; font-size: 300%;" >
<br>
${tableinfo.table_number }번 테이블<br>
<img alt="이미지없음" src="images/table/${tableinfo.seat_image }" width="600px" height="400px"><br>
	<br>
	콘센트:${ tableinfo.consent}<br>
	담요:${ tableinfo.blanket }<br>
	쿠션:${ tableinfo.cushion}<br>
<br>
<br>
<button onclick="Popclose()" style="font-size: 250%">닫기</button>
</div>