<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Users</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Backend/css/dataList.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="script.js"></script>
	<script src="js/utils.js"></script>
</head>
<body>
	<%--UPDATE DATA TABLE --%>
	<form onsubmit="window.location.href='Backend/addUser.jsp'; return false;" method="GET">
		<input type="submit" name="submit" value="ADD">
	</form>
	<form>
		<input type="hidden" name="command" value="SEARCH" />
		<input type="text" name="search" id="search" placeholder="Enter email or phone number"  required>
		<input type="submit" value="Find" />
	</form>
	
	<form action="UserController" method="get">
		<input type="hidden" name="command" value="LIST" />
		Page<input type="number" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }" required/>of ${noOfPages }
		<input type="submit" value="Load" />
	</form>
	
	<form action="UserController" method="get">
		<input type="hidden" name="command" value="ADMIN" />
		<input type="submit" value="Back" id="back"/>
	</form>
	
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
				<strong>Error!</strong> That email has already existed.
			</div>
		</c:when>
	</c:choose>
	
	<table>
		<tr>
			<th>Full Name</th>
			<th>Email</th>
			<th>Phone Number</th>
			<th>Address</th>
			<th>Account</th>
			<th>Role</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="user" items="${USER_LIST}" >
			<tr>
				<td>${user.fullName}</td>
				<td>${user.email}</td>
				<td>${user.phoneNumber}</td>
				<td>${user.address}</td>
				<td>${user.userName}</td>
				<td>${user.getRole(user.role_id)}</td>
				<td>${user.getStatus(user.status)}</td>
				
				<c:choose>
					<c:when test="${user.status}">
						<c:set var="status" value="LOCK" />
					</c:when>
					<c:otherwise>
	        			<c:set var="status" value="UNLOCK" />
    				</c:otherwise>
				</c:choose>
				
				<td>
					<form onsubmit="window.location.href='${pageContext.request.contextPath}/UserController'" method="get">
						<input type="hidden" name="command" value="${status }" />
						<input type="hidden" name="id" value="${user.id }">
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="status" value="${status }">
					</form>
					
					<form onsubmit="window.location.href='${pageContext.request.contextPath}/UserController'" method="get">
						<input type="hidden" name="command" value="DELETE" />
						<input type="hidden" name="id" value="${user.id }">
						<input type="hidden" name="pageNo" value="${pageNo }" min="1" max="${noOfPages }"/>
						<input type="submit" name="submit" value="DELETE" onclick="if (!confirm('Are you sure you want to delete USER: ${user.fullName}?')) return false">
					</form>
					
					<form action="UserController" method="get">
						<input type="hidden" name="id" value="${user.id }">
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