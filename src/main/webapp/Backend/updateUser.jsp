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
	<%--UPDATE DATA TABLE --%>
    <div class="container">
        <h2 class="mt-5 mb-3">Update account</h2>
        <form action="UserController" method="get" onsubmit="return validateForm()">
        
        	<%-- Sending 'UPDATE' command when valid --%>
        	<input type="hidden" name="command" value="UPDATE" />
        	<input type="hidden" name="id" value="${user.id }" />
         
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" value="${user.fullName }" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email }" class="form-control" readonly>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber }" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" value="${user.address }"  class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="account">Account:</label>
                    <input type="text" id="account" name="account" value="${user.userName }" class="form-control" readonly>
                </div>
                <div class="form-group col-md-6">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="${user.password }" class="form-control"  readonly>
                </div>
            </div>
            
            
            <div class="form-row">
            	<div class="form-group col-md-6">
                    <label for="status">Status:</label>
                    <select id="status" name="status" class="form-control">
                        <option value="true">Active</option>
                        <option value="false">Inactive</option>
                    </select>
                </div>
            
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
                    <input type="submit" value="Update" class="btn btn-danger">
                    <input type="reset" value="Close" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/UserController?command=LIST'">
                </div>
            </div>
        </form>
    </div>
</body>
</html>