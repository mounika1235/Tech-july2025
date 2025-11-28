<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4">
    <div class="row mb-4">
        <div class="col-md-4">
            <c:if test="${not empty movie.posterUrl}">
                <img src="${movie.posterUrl}" class="img-fluid rounded shadow-sm" alt="${movie.title}">
            </c:if>
        </div>
        <div class="col-md-8">
            <h2>${movie.title}</h2>
            <p><strong>Genre:</strong> ${movie.genre}</p>
            <p><strong>Language:</strong> ${movie.language}</p>
            <p><strong>Duration:</strong> ${movie.duration} min</p>
            <p>${movie.description}</p>

            <div class="mb-2">
                * <strong>${avgRating != null ? (avgRating) : 'No ratings yet'}</strong> / 5
            </div>

            <a href="${pageContext.request.contextPath}/admin/movies/${movie.id}/shows" class="btn btn-primary">
                View Showtimes
            </a>
        </div>
    </div>

    <hr/>

    <!-- Trailer -->
	<c:if test="${not empty movie.trailerUrl}">
	    <h4>Watch Trailer</h4>
	    <div class="ratio ratio-16x9 mb-4">
	        <iframe width="560" height="315" src="${movie.trailerUrl}"
	                frameborder="0" 
	                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
	                allowfullscreen></iframe>
	    </div>
	</c:if>
	<c:if test="${empty movie.trailerUrl}">
	    <div class="alert alert-info">
	        Trailer not available for this movie.
	    </div>
	</c:if>

    <!-- Reviews -->
    <h4> User Reviews</h4>
    <div id="reviews">
        <c:forEach var="r" items="${reviews}">
            <div class="card mb-2 p-2 shadow-sm">
                <strong>${r.user.username}</strong>
                <span class="text-warning">(${r.rating}*)</span>
                <p>${r.comment}</p>
                <small class="text-muted">${r.createdAt}</small>
            </div>
        </c:forEach>
        <c:if test="${empty reviews}">
            <div class="alert alert-secondary">No reviews yet. Be the first to review!</div>
        </c:if>
    </div>

    <!-- Add Review -->
    <div class="card mt-4 p-3">
        <h5>Leave a Review</h5>
        <form id="reviewForm">
            <input type="hidden" name="rating" id="ratingValue" value="0" />
            <div class="mb-2">
                <span class="star" data-value="1">*</span>
                <span class="star" data-value="2">*</span>
                <span class="star" data-value="3">*</span>
                <span class="star" data-value="4">*</span>
                <span class="star" data-value="5">*</span>
            </div>
            <textarea name="comment" class="form-control mb-2" placeholder="Write your review..." required></textarea>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</div>

<script>
    // Star rating logic
    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('ratingValue');
    stars.forEach(star => {
        star.addEventListener('click', () => {
            ratingValue.value = star.dataset.value;
            stars.forEach(s => s.classList.remove('selected'));
            star.classList.add('selected');
        });
    });

    // Submit review via AJAX
    document.getElementById('reviewForm').addEventListener('submit', e => {
        e.preventDefault();
        const data = new URLSearchParams(new FormData(e.target));
        fetch('${pageContext.request.contextPath}/admin/movies/${movie.id}/review', {
            method: 'POST',
            body: data
        }).then(res => res.text()).then(msg => {
            if (msg.startsWith("OK")) {
                alert('Review submitted!');
                window.location.reload();
            } else {
                alert(msg);
            }
        });
    });
</script>

<style>
    .star {
        font-size: 1.8rem;
        cursor: pointer;
        color: #ccc;
    }
    .star.selected,
    .star:hover {
        color: gold;
    }
</style>
