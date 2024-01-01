<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="bg-light">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<c:choose>
					<c:when test="${loginFail == 'loginFail'}">
						<div class="alert alert-danger alert-dismissible fade show mt-4">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Error!</strong> Email or password is incorrect. Or your account has been locked.
						</div>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${isSuccess == 'addSuccessfully'}">
						<div class="alert alert-success alert-dismissible fade show mt-4">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Success!</strong> Your change has been recorded.
						</div>
					</c:when>
					<c:when test="${isSuccess == 'dataExists'}">
						<div class="alert alert-danger alert-dismissible fade show mt-4">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Error!</strong> That email has already existed.
						</div>
					</c:when>
				</c:choose>

				<form action="${pageContext.request.contextPath}/PageController" method="get" class="mt-4">
					<input type="hidden" name="command" value="LOGIN" />
					<div class="form-group">
						<label for="email">Email:</label>
						<input type="email" placeholder="Enter your email" name="email" required class="form-control">
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<input type="password" name="password" required class="form-control">
					</div>
					<input type="submit" value="LOGIN" class="btn btn-primary">
					<% session.invalidate(); %>
					<a href="signup.jsp" class="d-block mt-3">Not have an account yet?</a>
				</form>

				<form action="${pageContext.request.contextPath}/PageController">
					<input type="hidden" name="command" value="HOME">
					<input type="submit" value="Back" class="btn btn-secondary mt-3"> 
				</form>
			</div>
		</div>
	</div>
</body>

</html>
