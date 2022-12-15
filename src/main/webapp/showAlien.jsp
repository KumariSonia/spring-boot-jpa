<%@page import="java.util.Map"%>
<%@page import="com.sonia.demo.model.Alien"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Technology</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${aliens}" var="alien">
			<tr>
				<td>${alien.getAid()}</td>
				<td>${alien.getaName()}</td>
				<td>${alien.getTech()}</td>
				<td><a href="editAlien?id=${alien.getAid()}">Edit</a></td>
				<td><a href="deleteAlien?id=${alien.getAid()}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

<a href="home.jsp">Add Users</a>

</body>
</html>