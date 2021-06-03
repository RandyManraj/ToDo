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

   <div class="col-sm-3 col-md-3">
    <h1>Register!</h1>
    <p class= "text-danger"><form:errors path="user.*"/></p>
    <form:form method="POST" action="/registration" modelAttribute="user">
        <p class="mb-3">
            <form:label path="firstName" class="form-label">First Name:</form:label>
            <form:input type="text" path="firstName" class="form-control"/>
        </p>
        <p class="mb-3">
            <form:label path="lastName" class="form-label">Last Name:</form:label>
            <form:input type="text" path="lastName" class="form-control"/>
        </p>
        <p class="mb-3">
            <form:label path="email" class="form-label">Email:</form:label>
            <form:input type="email" path="email" class="form-control"/>
        </p>
        <p class="mb-3">
            <form:label path="password" class="form-label">Password:</form:label>
            <form:password path="password" class="form-control"/>
        </p>
        <p class="mb-3">
            <form:label path="passwordConfirmation" class="form-label">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation" class="form-control"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>

</div> 
   <div class="col-sm-3 col-md-3">
    <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <p class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="text" id="email" name="email" class="form-control"/>
        </p>
        <p class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>    
    </div> 
</body>
</html>