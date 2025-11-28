<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4">
    <h2> Theater Management</h2>
    <a href="${pageContext.request.contextPath}/admin/theaters/add" class="btn btn-success mb-3">
        <i class="fa fa-plus"></i> Add Theater
    </a>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>City</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${theaters}">
            <tr>
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td>${t.city}</td>
                <td>${t.address}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/theaters/edit/${t.id}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/theaters/delete/${t.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
