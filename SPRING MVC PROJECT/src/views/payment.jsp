<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="container mt-5 col-md-6">
    <h3 class="mb-3">Confirm and Pay</h3>
    <div class="card p-3 shadow-sm">
        <p><strong>Booking ID:</strong> ${booking.id}</p>
        <p><strong>Movie:</strong> ${booking.show.movie.title}</p>
        <p><strong>Show Time:</strong> ${booking.show.showTime}</p>
        <p><strong>Seats:</strong> 
            <c:forEach var="seat" items="${booking.seats}" varStatus="status">
                ${seat.seatCode}<c:if test="${!status.last}">, </c:if>
            </c:forEach>
        </p>
        <p><strong>Amount:</strong> Rs. ${booking.amount}</p>
    </div>
    <form action="${pageContext.request.contextPath}/payment/complete" method="post" class="mt-3">
        <input type="hidden" name="bookingId" value="${booking.id}">
        <button type="submit" class="btn btn-success w-100">Pay Now</button>
    </form>
    <div class="mt-2">
        <a href="${pageContext.request.contextPath}/booking/history" class="btn btn-outline-secondary w-100">Cancel</a>
    </div>
</div>