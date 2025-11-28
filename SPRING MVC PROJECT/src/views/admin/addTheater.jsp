<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/theaters/${theater.id == null ? 'add' : 'edit'}">
        <input type="hidden" name="id" value="${theater.id}"/>
        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" value="${theater.name}" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>City</label>
            <input type="text" name="city" value="${theater.city}" class="form-control"/>
        </div>
        <div class="mb-3">
            <label>Address</label>
            <textarea name="address" class="form-control">${theater.address}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/theaters" class="btn btn-secondary">Cancel</a>
    </form>
</div>
