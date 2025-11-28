<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shows | ${movie.title}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h3>${movie.title} (${movie.language})</h3>
    <p>Genre: ${movie.genre}</p>

    <table class="table table-bordered mt-3">
        <thead><tr><th>Screen</th><th>Show Time</th><th>Book</th></tr></thead>
        <tbody>
        <c:forEach var="show" items="${shows}">
            <tr>
                <td>${show.screen.name}</td>
                <td>${show.showTime}</td>
                <td><a href="${pageContext.request.contextPath}/booking/selectSeats?showId=${show.id}"
                       class="btn btn-success btn-sm">Select Seats</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
