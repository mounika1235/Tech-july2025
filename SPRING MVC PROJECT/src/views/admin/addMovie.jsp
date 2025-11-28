<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/movies/add">
        <input type="hidden" name="id" value="${movie.id}" />

        <div class="mb-3">
            <label>Title</label>
            <input type="text" name="title" value="${movie.title}" class="form-control" required />
        </div>

        <div class="mb-3">
            <label>Language</label>
            <input type="text" name="language" value="${movie.language}" class="form-control" />
        </div>

        <div class="mb-3">
            <label>Genre</label>
            <input type="text" name="genre" value="${movie.genre}" class="form-control" />
        </div>

        <div class="mb-3">
            <label>Duration (in minutes)</label>
            <input type="number" name="duration" value="${movie.duration}" class="form-control" />
        </div>

        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control">${movie.description}</textarea>
        </div>

        <div class="mb-3">
            <label>Poster URL</label>
            <input type="text" name="posterUrl" value="${movie.posterUrl}" class="form-control" />
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/movies" class="btn btn-secondary">Cancel</a>
    </form>
</div>