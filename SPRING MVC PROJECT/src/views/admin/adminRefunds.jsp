<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2> Refund Management</h2>
    <table class="table table-striped table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Refund Txn ID</th>
                <th>Created</th>
                <th>Processed</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="r" items="${refunds}">
                <tr>
                    <td>${r.id}</td>
                    <td>Rs. ${r.amount}</td>
                    <td>${r.status}</td>
                    <td>${r.refundTxnId}</td>
                    <td>${r.createdAt}</td>
                    <td>${r.processedAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty refunds}">
        <div class="alert alert-info">No refunds found.</div>
    </c:if>
</div>