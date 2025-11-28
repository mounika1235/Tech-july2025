<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/templates/${template.id == null ? 'add' : 'edit'}">
        <input type="hidden" name="id" value="${template.id}"/>
        <input type="hidden" name="screen.id" value="${screen.id}"/>

        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" value="${template.name}" class="form-control" required/>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label>Rows</label>
                <input type="number" name="rows_no" value="${template.rows_no}" class="form-control" required/> <!-- Changed from rows to rows_no -->
            </div>
            <div class="col-md-6 mb-3">
                <label>Columns</label>
                <input type="number" name="cols" value="${template.cols}" class="form-control" required/>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/templates/screen/${screen.id}" class="btn btn-secondary">Cancel</a>
    </form>
</div>