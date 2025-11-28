<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>Confirm Booking</h2>
    <p>Booking ID: <strong>${booking.id}</strong></p>
    <p>Movie: <strong>${booking.show.movie.title}</strong></p>
    <p>Show Time: <strong>${booking.show.showTime}</strong></p>
    <p>Seats: 
        <c:forEach var="seat" items="${booking.seats}" varStatus="status">
            ${seat.seatCode}<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    </p>
    <p>Total Amount: <strong>Rs. ${booking.amount}</strong></p>
    <p>Status: <span class="badge bg-warning">${booking.status}</span></p>

    <form method="post" action="${pageContext.request.contextPath}/booking/confirm">
        <input type="hidden" name="bookingId" value="${booking.id}"/>
        <button type="submit" class="btn btn-success">Proceed to Payment</button>
    </form>
    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary mt-2">Cancel</a>
</div>