<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="it" items="${listado}">
		<div>
			<h2><a href="${it.url}">${it.url}</a></h2>
			<h3>${it.descripcion}</h3>
		</div>
	</c:forEach>
</body>
</html>