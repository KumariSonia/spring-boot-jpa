<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="addAlien">
		<input type="hidden" name="aid" value="${alien.get().getAid()}"><br>
		<input type="text" name="aName" value="${alien.get().getaName()}"><br>
		<input type="text" name="tech" value="${alien.get().getTech()}"><br>
		<input type="submit"><br>
	</form>

</body>
</html>