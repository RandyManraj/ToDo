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
<title>To Do List Organizer</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
    <h1>Welcome, ${loggedinuser.firstName} ${loggedinuser.lastName }!</h1>
    
    <h3>Everyone's Tasks To Do</h3><a href="/ideas/lowlikes"><button class="btn btn btn-warning">Low Likes</button></a><a href=/ideas/highlikes> <button class="btn btn btn-info">High Likes</button></a>

    <table class="table">
  <thead class="thead-dark">
    <tr>

      <th scope="col">Tasks</th>
      <th scope="col">Description:</th>
      <th scope="col">Created By:</th>
      <th scope="col">Number of Helpers</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${allideas }" var ="x">
    <tr>

      <td><a href="/tasks/${x.id}">${x.ideaTitle }</a></td>
      <td>${x.description}</td>
      <td><a href="/user/${x.creator.id}">${x.creator.firstName } ${x.creator.lastName }</a></td>
      <td>${x.likedUsers.size() }</td>
      <td>
	      <c:choose>
	           <c:when test="${x.likedUsers.contains(loggedinuser)}">
	                  <a href="/tasks/unlike/${x.id }">Undo</a>
	           </c:when>
	           <c:otherwise>
	                  <a href="/tasks/like/${x.id }">Assist</a>
	           </c:otherwise>
	      </c:choose>
     </td>
    </tr>
    </c:forEach>

  </tbody>
</table>
    <a href="/tasks/new"><button class="btn btn btn-info">Add Task</button></a>
    <a href="/logout"><button class="btn btn btn-danger">Logout</button></a>
<body>

</body>
</html>