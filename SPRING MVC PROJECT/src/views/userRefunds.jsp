<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="container mt-4">
    <h2> My Refunds</h2>
    
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">
            ← Back to Home
        </a>
    </div>
    
    <table class="table table-bordered mt-3">
        <thead class="table-light">
            <tr>
                <th>Refund ID</th>
                <th>Booking ID</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Processed At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="r" items="${refunds}">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.booking.id}</td>
                    <td>Rs. ${r.amount}</td>
                    <td>
                        <c:choose>
                            <c:when test="${r.status eq 'SUCCESS'}">
                                <span class="badge bg-success">SUCCESS</span>
                            </c:when>
                            <c:when test="${r.status eq 'INITIATED'}">
                                <span class="badge bg-warning">IN PROGRESS</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">FAILED</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty r.processedAt}">
                                ${r.processedAt}
                            </c:when>
                            <c:otherwise>
                                <span class="text-muted">Not processed yet</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/refunds/status/${r.id}" 
                           class="btn btn-sm btn-info">
                            View Details
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty refunds}">
        <div class="alert alert-info mt-3 text-center">
            <i class="fa fa-info-circle"></i> No refunds found.
        </div>
    </c:if>
</div>