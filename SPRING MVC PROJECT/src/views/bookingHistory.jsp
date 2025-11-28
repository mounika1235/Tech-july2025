<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>My Bookings</h2>
    
    <!-- Debug info -->
    <p>Total bookings: ${bookings.size()}</p>
    
    <c:if test="${empty bookings}">
        <div class="alert alert-info">
            <h4>No bookings found</h4>
            <p>You haven't made any bookings yet.</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Book Now</a>
        </div>
    </c:if>
    
    <c:if test="${not empty bookings}">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Movie</th>
                <th>Show Time</th>
                <th>Status</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bookings}">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.show.movie.title}</td>
                    <td>${b.show.showTime}</td>
                    <td>
                        <span class="badge 
                            <c:choose>
                                <c:when test="${b.status == 'CONFIRMED'}">bg-success</c:when>
                                <c:when test="${b.status == 'HOLD'}">bg-warning</c:when>
                                <c:when test="${b.status == 'CANCELLED'}">bg-danger</c:when>
                                <c:otherwise>bg-secondary</c:otherwise>
                            </c:choose>">
                            ${b.status}
                        </span>
                    </td>
                    <td>Rs. ${b.amount}</td>
                    <td>
                        <c:if test="${b.status == 'HOLD'}">
                            <a href="${pageContext.request.contextPath}/payment/start/${b.id}" 
                               class="btn btn-sm btn-primary">Pay Now</a>
                        </c:if>
                        <c:if test="${b.status == 'CONFIRMED'}">
                            <span class="text-success">✅ Paid</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">
        <i class="fa fa-arrow-left"></i> Back to Home
    </a>
</div>