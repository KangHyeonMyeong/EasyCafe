<%@ page contentType="text/html; charset=utf-8" %>
<%
	session.invalidate();

	response.sendRedirect("/temp/home.do");
%>
<html>
<head><title>로그아웃</title></head>
<body>
로그아웃 처리
</body>
</html>