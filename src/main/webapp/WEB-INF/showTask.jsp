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
<title>${thisidea.ideaTitle }</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>

<h1 class="alert alert-info">${thisidea.ideaTitle }</h1>
<h4 class="alert alert-secondary">${thisidea.description}</h4>
<h3 class="alert alert-danger">Created By:              ${thisidea.creator.firstName }  ${thisidea.creator.lastName }</h3>


<h1 class="alert alert-success">Users who are helping with task</h1>

<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Names</th>
      <th scope="col">Created On</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${thisidea.likedUsers }" var ="x">
    <tr>
      <td>${x.firstName} ${x.lastName }</td>
      <td>${thisidea.createdAt }</td>
    </tr>
	</c:forEach>
  </tbody>
</table>
<a href="/tasks"><button class="btn btn btn-primary"> To Do HomePage</button></a>
<a href="/tasks/${thisidea.id }/edit"><button class="btn btn-warning"> Edit this Page</button></a>
<a href="/tasks/${thisidea.id }/delete"><button class="btn btn-danger"> Delete this Idea</button></a>
</body>
</html>