<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Reviews</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Backend/css/dataList.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="script.js"></script>
	<script src="js/utils.js"></script>
</head>
<body>
	<%-- GO TO ADD DATA PAGE --%>
	<form onsubmit="window.location.href='Backend/addReview.jsp'; return false;" method="GET">
		<input type="submit" name="submit" value="ADD">
	</form>
	
	<%-- SEARCH BAR --%>
	<form>
		<input type="hidden" name="command" value="SEARCH" />
		<input type="text" name="search" id="search" placeholder="Enter rating..." required>
		<input type="submit" value="Find" />
	</form>
	
	<%-- PAGE NUMBER --%>
	<form action="ReviewController" method="get">
		<input type="hidden" name="command" value="LIST" />
		Page<input type="number" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }" required/>of ${noOfPages }
		<input type="submit" value="Load" />
	</form>
	
	<%-- RETURN TO ADMIN PAGE --%>
	<form action="UserController" method="get">
		<input type="hidden" name="command" value="ADMIN" />
		<input type="submit" value="Back" id="back"/>
	</form>

	<%-- DATA TABLE --%>
	<table>
		<tr>
			<th>Reviewer</th>
			<th>Post ID</th>
			<th>Rating</th>
			<th>Review date</th>
			<th>Content</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="review" items="${REVIEW_LIST}" >
			<tr>
				<td>${review.reviewer}</td>
				<td>${review.post_id}</td>
				<td>${review.rating}</td>
				<td>${review.reviewDate}</td>
				<td>${review.message }</td>
				<td>${review.getStatus(review.status)}</td>
				
				<c:choose>
					<c:when test="${review.status}">
						<c:set var="status" value="LOCK" />
					</c:when>
					<c:otherwise>
	        			<c:set var="status" value="UNLOCK" />
    				</c:otherwise>
				</c:choose>
				
				<td>
					<form onsubmit="window.location.href='${pageContext.request.contextPath}/ReviewController'" method="get">
						<input type="hidden" name="command" value="${status }" />
						<input type="hidden" name="id" value="${review.id }">
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="status" value="${status }">
					</form>
					
				</td>
				
			</tr>
		</c:forEach>
	</table>

</body>
</html>