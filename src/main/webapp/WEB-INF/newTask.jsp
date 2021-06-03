<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- c:out ; c:foreach; c:if  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    <!-- formatting things like dates  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
	<h1>Create a new Task</h1>
	
	<form:form action="/tasks/create" method="post" modelAttribute="ideas" class="col-sm-3 col-md-3">
            <p>
                <form:label path="ideaTitle" class="form-label">Name of Task</form:label>
                <form:input path="ideaTitle" class="form-control"/>
                <form:errors path="ideaTitle" class="alert alert-danger"/>
            </p>
            <p>
                <form:label path="description" class="form-label">Description</form:label>
                <form:input path="description" class="form-control"/>
                <form:errors path="description" class="alert alert-danger"/>
            </p>
            <input type="submit" value="Create" class="btn btn btn-primary"/>
            <a href="/tasks/" class="btn btn btn-warning">Home</a>
        </form:form> 
</body>
</html>