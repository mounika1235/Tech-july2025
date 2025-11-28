<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4">
    <h2> Manage Movies</h2>

    <form method="get" action="${pageContext.request.contextPath}/admin/movies" class="mb-3">
        <input type="text" name="search" value="${search}" placeholder="Search movies..." class="form-control" style="width:300px; display:inline-block;">
        <button type="submit" class="btn btn-primary">Search</button>
        <a href="${pageContext.request.contextPath}/admin/movies/add" class="btn btn-success float-end">
            <i class="fa fa-plus"></i> Add Movie
        </a>
    </form>

    <table class="table table-striped table-bordered mt-3">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Language</th>
            <th>Genre</th>
            <th>Duration (min)</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="m" items="${movies}">
            <tr>
                <td>${m.id}</td>
                <td>${m.title}</td>
                <td>${m.language}</td>
                <td>${m.genre}</td>
                <td>${m.duration}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/movies/edit/${m.id}" class="btn btn-sm btn-primary">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/movies/delete/${m.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure you want to delete this movie?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div> 