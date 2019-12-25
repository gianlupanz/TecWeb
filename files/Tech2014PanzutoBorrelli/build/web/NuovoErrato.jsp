<%-- 
    Document   : NuovoErrato
    Created on : 11-gen-2015, 16.33.57
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Editore</title>
    </head>
    <body>
        <h1>Documento già esistente,non è stato possibile scriverne uno con lo stesso titolo!</h1>
        <%@ include file="Crea_Pagina_Editore.jsp" %>
    </body>
</html>
