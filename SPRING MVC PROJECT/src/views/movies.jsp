<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container mt-4">
    <h2 class="mb-4 text-center">Now Showing</h2>

    <!-- Search Form -->
    <form method="get" action="${pageContext.request.contextPath}/admin/movies/Movies" class="d-flex justify-content-center mb-4">
        <input type="text" name="search" value="${search}" placeholder="Search by title or genre"
               class="form-control" style="width: 300px; margin-right: 10px;">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Movie Cards Grid -->
    <div class="row">
        <c:set var="displayedMovies" value="${0}" />
        <c:set var="usedGenres" value="" />
        
        <c:forEach var="m" items="${movies}" varStatus="loop">
            <c:if test="${displayedMovies < 10}">
                <c:set var="currentGenre" value="${m.genre.split(',')[0].trim()}" />
                
                <c:if test="${(not fn:contains(usedGenres, currentGenre)) or (fn:contains(usedGenres, currentGenre) and displayedMovies >= fn:length(usedGenres))}">
                    <div class="col-md-3 col-sm-6 mb-4">
                        <div class="card shadow-sm" style="border-radius: 10px;">
                            <c:if test="${not empty m.posterUrl}">
                                <img src="${m.posterUrl}" class="card-img-top" alt="${m.title}" style="height: 320px; object-fit: cover;">
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title">${m.title}</h5>
                                <p class="card-text">
                                    <small><strong>Language:</strong> ${m.language}</small><br/>
                                    <small><strong>Genre:</strong> ${m.genre}</small><br/>
                                    <small><strong>Duration:</strong> ${m.duration} min</small>
                                </p>

                                <div class="d-flex justify-content-between">
                                    <a href="${pageContext.request.contextPath}/admin/movies/${m.id}/shows" class="btn btn-outline-primary btn-sm">
                                        View Showtimes
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/movies/details/${m.id}" class="btn btn-outline-info btn-sm">
                                        Trailer
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <c:set var="displayedMovies" value="${displayedMovies + 1}" />
                    <c:if test="${not fn:contains(usedGenres, currentGenre)}">
                        <c:set var="usedGenres" value="${usedGenres},${currentGenre}" />
                    </c:if>
                </c:if>
            </c:if>
        </c:forEach>
    </div>

    <c:if test="${empty movies}">
        <div class="alert alert-info text-center">No movies found matching your search.</div>
    </c:if>
</div>