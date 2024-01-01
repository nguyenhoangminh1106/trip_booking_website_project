<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Post</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="text/css" href="${pageContext.request.contextPath }/Backend/css/addForm.css" />
    <script src="${pageContext.request.contextPath }/Backend/js/utils.js"></script>
</head>
<body>
	<%--UPDATE DATA TABLE --%>
    <div class="container">
        <h2 class="mt-5 mb-3">Update post</h2>
        <form id="addPost" action="PostController" method="get" onsubmit="return validatePostForm()">
        	<%-- Sending 'ADD' command when valid --%>
        	<input type="hidden" name="command" value="UPDATE" />
        	<input type="hidden" name="id" value="${post.id }" />
        	
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="postName">Post Name:</label>
                    <input type="text" id="postName" name="postName" value="${post.postName }" class="form-control">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                	
                    <label for="img_url">Image Link: </label>
                    <input type="text" id="img_url" name="img_url"  value="${post.img_url }" class="form-control">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="desc_url">Description Link:</label>
                    <input type="text" id="desc_url" name="desc_url"  value="${post.desc_url }" class="form-control">
                </div>
            </div>
            
            
            
            <div class="form-row">
                <div class="col text-right">
                    <input type="submit" value="Update" class="btn btn-danger">
                    <input type="reset" value="Close" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/PostController?command=LIST'">
                </div>
            </div>
        </form>
    </div>
</body>
</html>