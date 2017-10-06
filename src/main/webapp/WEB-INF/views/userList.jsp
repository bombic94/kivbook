<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kivbook User List</title>
</head>
<body>
	<center>
 
		<c:forEach items="${userList}" var="user">
			${user.id}: ${user.lastname}, ${user.firstname}<br>
		</c:forEach>
		
		<c:forEach items="${statusList}" var="status">
			${status.id}: ${status.user.firstname}, ${status.status_text}<br>
		</c:forEach>
		
		<c:forEach items="${commentList}" var="comment">
			${comment.id}: ${comment.user.firstname}, ${comment.comment_text}<br>
		</c:forEach>
		
		<c:forEach items="${likeList}" var="like">
			${like.id}: ${like.user.firstname}, ${like.status.status_text}<br>
		</c:forEach>
		
<%-- 		<c:forEach items="${friendshipList}" var="friendship"> --%>
<%-- 			${user.id}: ${user.lastname}, ${user.firstname}<br> --%>
<%-- 		</c:forEach> --%>
		
<%-- 		<c:forEach items="${chatList}" var="chat"> --%>
<%-- 			${user.id}: ${user.lastname}, ${user.firstname}<br> --%>
<%-- 		</c:forEach> --%>
		
<%-- 		<c:forEach items="${chat_LineList}" var="chat_Line"> --%>
<%-- 			${user.id}: ${user.lastname}, ${user.firstname}<br> --%>
<%-- 		</c:forEach> --%>
 
	</center>
</body>
</html>