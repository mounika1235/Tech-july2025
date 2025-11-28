<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>${message}</h1>	
 
	<c:if test="${empty currentUser}">
		<a href="/user/register"><button>Register</button></a>
		<a href="/user/login"><button>Login</button></a>
	</c:if>

	<c:if test="${not empty currentUser}">
		<p>Welcome, ${currentUser.username}!</p>
		<a href="/user/profile"><button>My Profile</button></a>
		
		<c:if test="${currentUser.role ne 'ADMIN'}">
			<a href="/refunds"><button>My Refunds</button></a>
		</c:if>
		
		<a href="/user/logout"><button>Logout</button></a>
		
		<c:if test="${currentUser.role eq 'ADMIN'}">
			<br/><br/>
			<a href="/admin/dashboard"><button>Admin Dashboard</button></a>
		</c:if>
	</c:if>
	
	<!-- Regular user movie browsing -->
	<c:if test="${not empty currentUser && currentUser.role ne 'ADMIN'}">
		<br/><br/>
		<a href="/admin/movies/Movies"><button>Browse Movies</button></a>
	</c:if>
</body>
</html>