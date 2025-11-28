<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/shows/add">
        <input type="hidden" name="screen.id" value="${screen.id}" />

        <div class="mb-3">
            <label>Movie</label>
            <select name="movie.id" class="form-select" required>
                <c:forEach var="m" items="${movies}">
                    <option value="${m.id}">${m.title}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Seat Template</label>
            <select name="seatTemplate.id" class="form-select" required>
                <c:forEach var="t" items="${templates}">
                    <option value="${t.id}">${t.name} (${t.rows_no}x${t.cols})</option> <!-- Changed from rows to rows_no -->
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Show Time</label>
            <input type="datetime-local" name="showTime" class="form-control" required/>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/shows/screen/${screen.id}" class="btn btn-secondary">Cancel</a>
    </form>
</div>