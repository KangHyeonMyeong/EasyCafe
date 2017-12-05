<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="txet/html; charset=UTF-8">
<title></title>
</head>
<body>
<c:if test="${! empty member}">
	로그인 되었다
</c:if>

<c:if test="${ empty member }">
	로그아웃 되었다
</c:if>
</body>
</html>