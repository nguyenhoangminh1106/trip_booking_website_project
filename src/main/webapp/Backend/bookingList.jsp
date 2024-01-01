<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Trips</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Backend/css/dataList.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="script.js"></script>
	<script src="js/utils.js"></script>
</head>
<body>
	<%-- SEARCH BAR --%>

	<form>
		<input type="hidden" name="command" value="SEARCH" />
		<input type="text" name="search" id="search" placeholder="Enter booker's name..." required>
		<input type="submit" value="Find" />
	</form>
	
	<%-- PAGE NUMBER --%>
	<form action="BookingController" method="get">
		<input type="hidden" name="command" value="LIST" />
		Page<input type="number" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }" required/>of ${noOfPages }
		<input type="submit" value="Load" />
	</form>
	
	<%-- RETURN TO ADMIN PAGE --%>
	<form action="UserController" method="get">
		<input type="hidden" name="command" value="ADMIN" />
		<input type="submit" value="Back" id="back"/>
	</form>

	<%-- ADD DATA ALERT SUCCESS/ERROR --%>
	<c:if test="${addSuccessMessage }">
		<div class="alert alert-success alert-dismissible fade show">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Success!</strong> Your change has been recorded.
		</div>
	</c:if>

	<%-- DATA TABLE --%>
	<table>
		<tr>
			<th>Trip Name</th>
			<th>Total Amount</th>
			<th>Booker</th>
			<th>Adults</th>
			<th>Children</th>
			<th>Booking date</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="booking" items="${BOOKING_LIST}" >
			<tr>
				<td>${booking.tripName}</td>
				<td>${booking.price}<br>(million vnd)</td>
				<td>${booking.booker }</td>
				<td>${booking.noOfAdults}</td>
				<td>${booking.noOfChildren}</td>
				<td>${booking.bookingDate}</td>
				<td>${booking.getStatus(booking.status)}</td>
				
				<%-- Check status --%>
				<c:choose>
					<c:when test="${booking.status}">
						<c:set var="status" value="LOCK" />
					</c:when>
					<c:otherwise>
	        			<c:set var="status" value="UNLOCK" />
    				</c:otherwise>
				</c:choose>
				
				<td>
					<form onsubmit="window.location.href='${pageContext.request.contextPath}/BookingController'" method="get">
						<input type="hidden" name="command" value="${status }" />
						<input type="hidden" name="id" value="${booking.id }">
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="status" value="${status }">
					</form>
					
				</td>
				
			</tr>
		</c:forEach>
	</table>

</body>
</html>