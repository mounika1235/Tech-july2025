<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4 text-center">
    <div class="card shadow-sm p-4">
        <div class="card-body">
            <h2 class="text-success">🎉 Booking Confirmed!</h2>
            <div class="mt-4">
                <p><strong>Booking ID:</strong> ${booking.id}</p>
                <p><strong>Movie:</strong> ${booking.show.movie.title}</p>
                <p><strong>Show Time:</strong> ${booking.show.showTime}</p>
                <p><strong>Total Paid:</strong> Rs. ${booking.amount}</p>
                <p><strong>Status:</strong> <span class="badge bg-success">${booking.status}</span></p>
            </div>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/booking/history" class="btn btn-primary">View Booking History</a>
                <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Back to Home</a>
            </div>
        </div>
    </div>
</div>