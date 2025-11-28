<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register | Movie Ticket Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5 col-md-6">
    <h2 class="text-center mb-4">Create an Account</h2>
    <form action="${pageContext.request.contextPath}/user/register" method="post" class="card p-4 shadow-sm">
        <div class="mb-3">
            <label>Username</label>
            <input type="text" name="username" class="form-control" required />
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required />
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-primary w-100">Register</button>
        <p class="text-center mt-3">Already have an account? 
           <a href="${pageContext.request.contextPath}/user/login">Login here</a>
        </p>
    </form>
</div>
</body>
</html>
