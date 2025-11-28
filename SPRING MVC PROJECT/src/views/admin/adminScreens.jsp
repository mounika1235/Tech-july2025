<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container mt-4">
    <h2> Manage Screens for ${theater.name}</h2>
    <a href="${pageContext.request.contextPath}/admin/screens/add/${theater.id}" class="btn btn-success mb-3">
        <i class="fa fa-plus"></i> Add Screen
    </a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Seat Templates</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${screens}">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.description}</td>
                <td>
                    <c:if test="${not empty s.seatTemplates}">
                        ${fn:length(s.seatTemplates)} templates
                    </c:if>
                    <c:if test="${empty s.seatTemplates}">
                        None
                    </c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/screens/edit/${s.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/screens/delete/${s.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this screen?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/admin/theaters" class="btn btn-secondary mt-2"> Back to Theaters</a>
</div>