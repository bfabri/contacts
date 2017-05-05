<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
</head>
<body>
	<div style="width: 50%">
		<form class="form-signin" action="<c:url value='/login/authenticate'/>" method="post">
			<h2 class="form-signin-heading">Faça login para acessar a agenda de contatos</h2>
			<input type="text" class="form-control" name="user.email" placeholder="Email" /> 
			<input type="password" class="form-control" name="user.password" placeholder="Senha" />
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		</form>
		
		<c:if test="${not empty errors}">
	        <div class="alert alert-danger">
	            <c:forEach var="error" items="${errors}">
	                ${error.category} - ${error.message}<br />
	            </c:forEach>
	        </div>
	    </c:if>
    </div>
</body>
</html>