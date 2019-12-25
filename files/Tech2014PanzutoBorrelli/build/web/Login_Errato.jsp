<%-- 
    Document   : Login_Errato
    Created on : 9-gen-2015, 10.15.43
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>LOGIN FALLITO</h1>
        <%@include file="Login.html"%>
    </body>
</html>
