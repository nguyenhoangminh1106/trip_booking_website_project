<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Form</title>
		<script src="${pageContext.request.contextPath }/FrontEnd/js/utils.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	</head>
<body class="bg-light">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<form action="${pageContext.request.contextPath}/PageController" name="signupForm" method="get" onsubmit="return re_enterPasswordMatch()" class="mt-4">
					<input type="hidden" name="command" value="SIGNUP" />
					<div class="form-group">
						<label for="email">Email:</label>
						<input type="email" name="email" required class="form-control">
					</div>
					<div class="form-group">
						<label for="fullName">Full Name:</label>
						<input type="text" name="fullName" required class="form-control">
					</div>
					<div class="form-group">
						<label for="account">User Name:</label>
						<input type="text" name="account" required class="form-control">
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<input type="password" name="password" required class="form-control">
					</div>
					<div class="form-group">
						<label for="confirmPassword">Confirm Password:</label>
						<input type="password" name="confirmPassword" required class="form-control">
					</div>
					<div class="form-group">
						<label for="phoneNumber">Phone Number:</label>
						<input type="tel" name="phoneNumber" required class="form-control">
					</div>
					<div class="form-group">
						<label for="address">Address:</label>
						<input type="text" name="address" required class="form-control">
					</div>
					<input type="submit" value="Submit" class="btn btn-primary">
				</form>
				
				<button onclick="window.location.href='${pageContext.request.contextPath}/FrontEnd/login.jsp'" class="btn btn-secondary mt-3">Back</button>
			</div>
		</div>
	</div>
</body>

</html>
