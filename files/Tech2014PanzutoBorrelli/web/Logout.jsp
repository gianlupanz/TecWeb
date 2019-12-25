<%-- 
    Document   : Logout
    Created on : 9-gen-2015, 11.51.32
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
        <h1>A Presto...!</h1>
        <% session.setAttribute("Username",null);%>
        <%@ include file="index.html"%>
    </body>
</html>
