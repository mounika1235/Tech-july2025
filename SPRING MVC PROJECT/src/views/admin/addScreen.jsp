<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/screens/${screen.id == null ? 'add' : 'edit'}">
        <input type="hidden" name="id" value="${screen.id}"/>
        <input type="hidden" name="theater.id" value="${theater.id}"/>
        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" value="${screen.name}" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control">${screen.description}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/screens/theater/${theater.id}" class="btn btn-secondary">Cancel</a>
    </form>
</div>