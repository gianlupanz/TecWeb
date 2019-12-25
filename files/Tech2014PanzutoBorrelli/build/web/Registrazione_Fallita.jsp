<%-- 
    Document   : Registrazione_Fallita
    Created on : 9-gen-2015, 10.32.24
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRAZIONE</title>
    </head>
    <body>
        <h1>USERNAME IN USO,RIPROVA CON UN USERNAME DIVERSO!</h1>
        <%@include file="Registrazione.html"%>
    </body>
</html>
