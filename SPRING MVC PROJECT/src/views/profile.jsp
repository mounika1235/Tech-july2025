<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- The header, navbar, and footer will be included automatically by layout.jsp -->

<div class="row justify-content-center mt-4">
    <div class="col-md-8 col-lg-6">
        <div class="card shadow-sm border-0">
            <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
                <h5 class="mb-0">My Profile</h5>
                <i class="fa fa-user-circle fa-lg"></i>
            </div>
            <div class="card-body p-4">

                <c:if test="${not empty message}">
                    <div class="alert alert-success">${message}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/user/updateProfile" method="post">
                    <div class="mb-3">
                        <label class="form-label">Full Name</label>
                        <input type="text" name="fullName" value="${user.fullName}" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email Address</label>
                        <input type="email" name="email" value="${user.email}" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Phone Number</label>
                        <input type="text" name="phone" value="${user.phone}" class="form-control" pattern="[0-9]{10}" title="Enter a valid 10-digit number" />
                    </div>

                    <hr />

                    <div class="mb-3">
                        <label class="form-label">Change Password (optional)</label>
                        <input type="password" name="password" class="form-control" placeholder="Enter new password if you wish to change" />
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">
                            <i class="fa fa-arrow-left"></i> Back
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-save"></i> Save Changes
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
