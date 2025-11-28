<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>Shows for ${screen.name}</h2>
    <a href="${pageContext.request.contextPath}/admin/shows/add/${screen.id}" class="btn btn-success mb-3">
        <i class="fa fa-plus"></i> Add Show
    </a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Show Time</th>
            <th>Total Seats</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${shows}">
            <tr>
                <td>${s.id}</td>
                <td>${s.showTime}</td>
                <td>${s.totalSeats}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/shows/delete/${s.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this show?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/admin/screens/theater/${screen.theater.id}" class="btn btn-secondary mt-2">
         Back to Screens
    </a>
</div>