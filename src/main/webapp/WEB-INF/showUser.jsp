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
<title>${thisuser.firstName }</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
	<h1 class="alert alert-info">Show User Page</h1>
	<h1 class="alert alert-secondary">${thisuser.firstName }</h1>
	
	<h3>Tasks created by ${thisuser.firstName }</h3>

	
    <table class="table">
  <thead class="thead-dark">
    <tr>

      <th scope="col">Tasks</th>
      <th scope="col">Description:</th>
      <th scope="col">Created By:</th>


    </tr>
  </thead>
  <tbody>
  <c:forEach items="${allideas }" var ="x">
    <tr>

      <td><a href="/tasks/${x.id}">${x.ideaTitle }</a></td>
      <td>${x.description}</td>
      <td><a href="/user/${x.creator.id}">${x.creator.firstName } ${x.creator.lastName }</a></td>

      <td>
	      
     </td>
    </tr>
    </c:forEach>

  </tbody>
</table>
<a href="/ideas/new"><button class="btn btn btn-info">Back to Dashboard</button></a>
</body>
</html>