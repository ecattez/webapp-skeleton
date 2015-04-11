<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="CSS" value="../css" />
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${ CSS }/style.css" />
		<title>Insert title here</title>
	</head>
	<body>
		<c:forEach items="${ it.names }" var="name">
			<h1>Hello ${ name }</h1>
		</c:forEach>
	</body>
</html>