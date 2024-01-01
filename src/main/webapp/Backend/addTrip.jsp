<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Trip</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="text/css" href="${pageContext.request.contextPath }/Backend/css/addForm.css" />
    <script src="${pageContext.request.contextPath }/Backend/js/utils.js"></script>
</head>
<body>
	<%--ADD TRIP FORM --%>

    <div class="container">
        <h2 class="mt-5 mb-3">Add new trip</h2>
        <form id="addTrip" action="../TripController" method="get" onsubmit="return validateTripForm()">
        	<%-- Sending 'ADD' command when valid --%>
        	<input type="hidden" name="command" value="ADD" />
        	
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="tripName">Trip Name:</label>
                    <input type="text" id="tripName" name="tripName" class="form-control">
                </div>
                <div class="form-group col-md-6">
                    <label for="price">Price (million vnd):</label>
                    <input type="number" step="0.01" id="price" name="price" class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                	
                    <label for="img_url">Image Link: </label>
                    <input type="text" id="img_url" name="img_url" class="form-control">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="desc_url">Description Link:</label>
                    <input type="text" id="desc_url" name="desc_url" class="form-control">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" class="form-control">
                </div>
            </div>
            
            <%DateFormat currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
            
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="startDate">Start Date:</label>
                    <input type="date" id="startDate" name="startDate" class="form-control"
                    		placeholder="dd/MM/yyyy" min="<%= currentDate.format(new java.util.Date())%>" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="endDate">End Date:</label>
                    <input type="date" id="endDate" name="endDate" class="form-control"
                    		placeholder="dd/MM/yyyy" min="<%= currentDate.format(new java.util.Date())%>" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="col text-right">
                    <input type="submit" value="Add" class="btn btn-danger">
                    <input type="reset" value="Close" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/TripController?command=LIST'">
                </div>
            </div>
        </form>
    </div>
</body>
</html>