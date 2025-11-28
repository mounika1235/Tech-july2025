<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5">
    <div class="card p-4 text-center shadow-sm">
        <h3>Refund Status Details</h3>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <p><strong>Refund ID:</strong> ${refund.id}</p>
                <p><strong>Booking ID:</strong> ${refund.booking.id}</p>
                <p><strong>Amount:</strong> Rs. ${refund.amount}</p>
            </div>
            <div class="col-md-6">
                <p><strong>Status:</strong> 
                   <span class="badge ${refund.status eq 'SUCCESS' ? 'bg-success' : 
                                       refund.status eq 'INITIATED' ? 'bg-warning' : 'bg-danger'}">
                       ${refund.status}
                   </span>
                </p>
                <p><strong>Initiated:</strong> ${refund.createdAt}</p>
                <c:if test="${not empty refund.processedAt}">
                    <p><strong>Processed:</strong> ${refund.processedAt}</p>
                </c:if>
            </div>
        </div>
        
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/refunds" class="btn btn-secondary">
                ← Back to My Refunds
            </a>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">
                Back to Home
            </a>
        </div>
    </div>
</div>