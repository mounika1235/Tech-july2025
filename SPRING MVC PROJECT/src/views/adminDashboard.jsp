<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="container mt-4">
    <h2 class="mb-4 text-center">
        🎭 Theater Admin Dashboard
    </h2>

    <div class="alert alert-info text-center">
        Welcome, <strong>${currentUser.fullName}</strong>!  
        Manage your theater's movies, screens, and show schedules below.
    </div>

    <!-- Cards for navigation -->
    <div class="row">
        <div class="col-md-3 mb-4">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h5 class="card-title">🎞 Movies</h5>
                    <p class="card-text">Manage movies in your theater</p>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-primary">Manage Movies</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h5 class="card-title">📺 Screens</h5>
                    <p class="card-text">Manage theater screens</p>
                    <a href="${pageContext.request.contextPath}/admin/screens/theater/1" class="btn btn-primary">Manage Screens</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h5 class="card-title">🕒 Shows</h5>
                    <p class="card-text">Manage show schedules</p>
                    <a href="${pageContext.request.contextPath}/admin/shows/screen/1" class="btn btn-primary">Manage Shows</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h5 class="card-title">💰 Refunds</h5>
                    <p class="card-text">Manage refund requests</p>
                    <a href="${pageContext.request.contextPath}/refunds/admin" class="btn btn-primary">Manage Refunds</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Movies Management Section -->
    <div class="mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h4>🎞 Movies Management</h4>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addMovieModal">
                <i class="fa fa-plus"></i> Add New Movie
            </button>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Language</th>
                        <th>Duration</th>
                        <th>Genre</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td>${movie.id}</td>
                            <td><strong>${movie.title}</strong></td>
                            <td>${movie.language}</td>
                            <td>${movie.duration} mins</td>
                            <td><span class="badge bg-secondary">${movie.genre}</span></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/deleteMovie" method="post" class="d-inline">
                                    <input type="hidden" name="movieId" value="${movie.id}" />
                                    <button type="submit" class="btn btn-sm btn-danger" 
                                            onclick="return confirmDelete('${movie.title}')">
                                        <i class="fa fa-trash"></i> Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty movies}">
                        <tr>
                            <td colspan="6" class="text-center text-muted py-4">
                                <i class="fa fa-film fa-2x mb-2"></i><br>
                                No movies found. Add your first movie!
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Add Movie Modal -->
<div class="modal fade" id="addMovieModal" tabindex="-1" aria-labelledby="addMovieModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-dark text-white">
        <h5 class="modal-title" id="addMovieModalLabel"><i class="fa fa-plus"></i> Add New Movie</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="${pageContext.request.contextPath}/admin/movies/add" method="post">
        <div class="modal-body">
            <div class="mb-3">
                <label class="form-label">Movie Title *</label>
                <input type="text" name="title" class="form-control" placeholder="Enter movie title" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Language *</label>
                <input type="text" name="language" class="form-control" placeholder="e.g., English, Hindi, Telugu" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Duration (minutes) *</label>
                <input type="number" name="duration" class="form-control" placeholder="Enter duration in minutes" min="1" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Genre</label>
                <input type="text" name="genre" class="form-control" placeholder="e.g., Action, Romance, Drama" />
            </div>
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea name="description" class="form-control" placeholder="Enter movie description" rows="3"></textarea>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-success">Add Movie</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Success/Error Message Display -->
<c:if test="${not empty successMessage}">
    <div class="toast-container position-fixed top-0 end-0 p-3">
        <div class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="fa fa-check-circle me-2"></i>${successMessage}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty errorMessage}">
    <div class="toast-container position-fixed top-0 end-0 p-3">
        <div class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="fa fa-exclamation-circle me-2"></i>${errorMessage}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
</c:if>

<!-- JavaScript for functionality -->
<script>
document.addEventListener('DOMContentLoaded', function() {
    // Initialize toasts
    var toastElList = [].slice.call(document.querySelectorAll('.toast'));
    var toastList = toastElList.map(function(toastEl) {
        return new bootstrap.Toast(toastEl);
    });
    
    // Show all toasts
    toastList.forEach(function(toast) {
        toast.show();
    });
});

// Confirm delete function
function confirmDelete(movieTitle) {
    return confirm('Are you sure you want to delete "' + movieTitle + '"? This action cannot be undone.');
}

// Handle modal form submission
const addMovieForm = document.querySelector('#addMovieModal form');
if (addMovieForm) {
    addMovieForm.addEventListener('submit', function(e) {
        const title = this.querySelector('input[name="title"]').value;
        const language = this.querySelector('input[name="language"]').value;
        const duration = this.querySelector('input[name="duration"]').value;
        
        if (!title || !language || !duration) {
            e.preventDefault();
            alert('Please fill all required fields');
        }
    });
}
</script>