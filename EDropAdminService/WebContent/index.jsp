<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	yes
	<span>666</span>
	<c:forEach items="${pageInfo.list }" var="p">
		<span>666</span>
		<span>${p.username }</span>
		<span>${p.registerTime }</span>
		<span>${p.phone }</span>
		<span>${p.address }</span>
		<span>${p.detailAddress }</span>
		<br/>
	</c:forEach>
</body>
</html>