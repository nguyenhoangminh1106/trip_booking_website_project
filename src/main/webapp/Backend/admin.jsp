<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Backend/css/admin.css">


</head>
<body>

<h1>Choose Action</h1>

	<%-- CHOOSE DATA TABLE TO MANAGE --%>

<button onclick="window.location.href='${pageContext.request.contextPath}/UserController?command=LIST'" >Manage Users</button>
<button onclick="window.location.href='${pageContext.request.contextPath}/TripController?command=LIST'">Manage Trips</button>
<button onclick="window.location.href='${pageContext.request.contextPath}/BookingController?command=LIST'">Manage Bookings</button>
<button onclick="window.location.href='${pageContext.request.contextPath}/PostController?command=LIST'">Manage Posts</button>
<button onclick="window.location.href='${pageContext.request.contextPath}/ReviewController?command=LIST'">Manage Reviews</button>

</body>
</html>