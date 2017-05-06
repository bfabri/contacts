<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de contatos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/bootstrap.css"/>">
    </head>
    <body>
    	<a href="<c:url value="/logout" />">Logout</a>
    	
        <h1>Formulário de contato</h1>
        
        <c:if test="${not empty errors}">
        	<div class="alert alert-danger">
        		<ul>
			        <c:forEach var="error" items="${errors}">
			        	<li>${error.message}</li>
					</c:forEach>
				</ul>
        	</div>
		</c:if>

        <form action="<c:url value='/save'/>" method="post">
        	<input type="hidden" name="contact.id" value="${contact.id}" />
            Nome: <input class="form-control" type="text" name="contact.name" value="${fn:escapeXml(contact.name)}" />
            Telefone: <input class="form-control" type="text" name="contact.phone" value="${fn:escapeXml(contact.phone)}" />
            Email: <input class="form-control" type="text" name="contact.email" value="${fn:escapeXml(contact.email)}"/>
           <input type="submit" class="btn btn-primary" value="Salvar" />
        </form>
    </body>
</html>