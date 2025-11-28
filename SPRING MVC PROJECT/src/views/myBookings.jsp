<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>My Bookings</h2>
    <c:if test="${empty bookings}">
        <p>No bookings found.</p>
    </c:if>
    <c:if test="${not empty bookings}">
        <table class="table table-striped mt-3">
            <thead>
                <tr><th>Booking ID</th><th>Amount</th><th>Status</th><th>Booking Time</th><th>Action</th></tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.id}</td>
                        <td>Rs.${booking.amount}</td>
                        <td>${booking.status}</td>
                        <td>${booking.bookingTime}</td>
                        <td>
                            <c:if test="${booking.status eq 'CONFIRMED'}">
                                <td>
    <c:if test="${booking.status eq 'CONFIRMED'}">
        <a href="${pageContext.request.contextPath}/payment/start-test/${booking.id}" class="btn btn-success btn-sm">Make Payment</a>
    </c:if>
</td>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>