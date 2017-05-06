<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de contatos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/bootstrap.css"/>">
    </head>
    <body>
    	<h1>Lista de contatos</h1>
        
        <c:if test="${not empty message}">
    		<div class="alert alert-success">${message}</div>
		</c:if>
		
        <table class="table table-stripped table-hover table-bordered">
        	<thead>
            	<tr>
                	<th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                </tr>
        	</thead>
            <tbody>
            	<c:forEach items="${contacts}" var="contact">
                	<tr>
                    	<td><c:out value="${contact.name}"/></td>
                    	<td><c:out value="${contact.phone}"/></td>
                    	<td><c:out value="${contact.email}"/></td>
                	</tr>
            	</c:forEach>
			</tbody>
        </table>
        <a href="<c:url value='/form'/>">Adicionar novo contato</a>
    </body>
</html>