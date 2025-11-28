<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"> MovieTickets</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">

        <!-- Guest Navigation -->
        <c:if test="${empty sessionScope.user}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/login">
              <i class="fa fa-sign-in-alt"></i> Login
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/register">
              <i class="fa fa-user-plus"></i> Register
            </a>
          </li>
        </c:if>

        <!-- Logged In User Navigation -->
        <c:if test="${not empty sessionScope.user}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/movies/search">
              <i class="fa fa-film"></i> Movies
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/bookings">
              <i class="fa fa-ticket-alt"></i> My Bookings
            </a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
              <i class="fa fa-user"></i> ${sessionScope.user.username}
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/user/logout">
                  <i class="fa fa-sign-out-alt"></i> Logout</a></li>
            </ul>
          </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
