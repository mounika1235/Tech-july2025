<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5 col-md-6">
    <h3 class="mb-3">Final Payment Confirmation</h3>
    <div class="card p-3 shadow-sm">
        <p><strong>Booking ID:</strong> ${booking.id}</p>
        <p><strong>Movie:</strong> ${booking.show.movie.title}</p>
        <p><strong>Seats:</strong> ${booking.seats.size()}</p>
        <p><strong>Amount:</strong> Rs.${booking.amount}</p>
        <p><strong>Payment ID:</strong> ${payment.txnId}</p>
    </div>
    <form action="${pageContext.request.contextPath}/booking/confirm" method="post" class="mt-3">
        <input type="hidden" name="bookingId" value="${booking.id}">
        <button type="submit" class="btn btn-success w-100">Confirm Payment</button>
    </form>
</div>
</body>
</html>