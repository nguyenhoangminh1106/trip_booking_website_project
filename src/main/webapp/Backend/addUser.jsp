<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="text/css" href="${pageContext.request.contextPath }/Backend/css/addForm.css" />
    <script src="${pageContext.request.contextPath }/Backend/js/utils.js"></script>
</head>
<body>
	<%--ADD TRIP FORM --%>

    <div class="container">
        <h2 class="mt-5 mb-3">Add new account</h2>
        <form action="../UserController" method="get" onsubmit="return validateForm()">
        	<%-- Sending 'ADD' command when valid --%>
        	<input type="hidden" name="command" value="ADD" />
        	
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="account">Account:</label>
                    <input type="text" id="account" name="account" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="role">Role:</label>
                    <select id="role" name="role" class="form-control">
                        <option value="Admin">Admin</option>
                        <option value="User">User</option>
                    </select>
                </div>
            </div>
            
            <div class="form-row">
                <div class="col text-right">
                    <input type="submit" value="Add" class="btn btn-danger">
                    <input type="reset" value="Close" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/UserController?command=LIST'">
                </div>
            </div>
        </form>
    </div>
</body>
</html>