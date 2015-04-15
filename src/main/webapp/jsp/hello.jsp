<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${ css }/style.css" />
		<title>Hello world</title>
	</head>
	<body>
		<c:forEach items="${ it.names }" var="name">
			<h1>Hello ${ name }</h1>
		</c:forEach>
	</body>
</html>