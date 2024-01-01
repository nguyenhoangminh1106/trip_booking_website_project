<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Posts</title>
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
	<form onsubmit="window.location.href='Backend/addPost.jsp'; return false;" method="GET">
		<input type="submit" name="submit" value="ADD">
	</form>
	
	<%-- SEARCH BAR --%>
	<form>
		<input type="hidden" name="command" value="SEARCH" />
		<input type="text" name="search" id="search" placeholder="Enter post's name..." required>
		<input type="submit" value="Find" />
	</form>
	
	<%-- PAGE NUMBER --%>
	<form action="PostController" method="get">
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
	<c:choose>
		<c:when test="${isSuccess == 'addSuccessfully'}">
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong> Your change has been recorded.
			</div>
		</c:when>
		<c:when test="${isSuccess == 'dataExists'}">
			<div class="alert alert-danger alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> That post has already existed.
			</div>
		</c:when>
	</c:choose>

	<%-- DATA TABLE --%>
	<table>
		<tr>
			<th>Post Name</th>
			<th>Depiction</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<c:forEach var="post" items="${POST_LIST}" >
			<tr>
				<td>${post.postName}</td>
				<td>
					<img src="${post.img_url}" alt="${post.postName}" style="width: 100px; height: 100px;"/>
				</td>
				<td>
					<a href="${post.desc_url}" target="_blank">${post.postName}</a>
				</td>
				
				<td>
					
					<form onsubmit="window.location.href='${pageContext.request.contextPath}/PostController'" method="get">
						<input type="hidden" name="command" value="DELETE" />
						<input type="hidden" name="id" value="${post.id }">
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="submit" value="DELETE" onclick="if (!confirm('Are you sure you want to delete POST: ${post.postName}?')) return false">
					</form>
					
					<form action="PostController" method="get">
						<input type="hidden" name="id" value="${post.id }">
						<input type="hidden" name="command" value="UPDATE_FORM" />
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="submit" value="UPDATE">
					</form>
					
				</td>
				
			</tr>
		</c:forEach>
	</table>

</body>
</html>