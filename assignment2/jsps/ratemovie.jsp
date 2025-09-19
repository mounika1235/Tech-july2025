<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="rateMovie" method="post">
    User ID: <input type="number" name="userId" required/><br/>
    Movie ID: <input type="number" name="movieId" required/><br/>
    Rating (1-5): <input type="number" name="rating" min="1" max="5" required/><br/>
    <input type="submit" value="Rate Movie"/>
</form>

</body>
</html>