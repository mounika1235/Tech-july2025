<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login | Movie Ticket Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5 col-md-5">
    <h2 class="text-center mb-4">User Login</h2>
    <form action="${pageContext.request.contextPath}/user/login" method="post" class="card p-4 shadow-sm">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <div class="mb-3">
            <label>Username</label>
            <input type="text" name="username" class="form-control" required />
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-success w-100">Login</button>
        <p class="text-center mt-3">New user? 
           <a href="${pageContext.request.contextPath}/user/register">Register now</a>
        </p>
    </form>
</div>
</body>
</html>
