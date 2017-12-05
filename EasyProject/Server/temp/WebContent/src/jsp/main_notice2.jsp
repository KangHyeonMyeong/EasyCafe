<%@page import="com.cafe.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title></title>
</head>
<body>

<table id="notice_main">
<c:forEach items="${articleList }" var="article">
	<tr>
		<td>${article.num }</td>
		<td><a href="#">${article.title }</a></td>
		<td>${article.e_mail }</td>
		<td>${article.notice_time }</td>
	</tr>
</c:forEach>

</table>

</body>
</html>