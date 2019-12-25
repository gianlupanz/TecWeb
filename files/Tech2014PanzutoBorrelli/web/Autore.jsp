<%-- 
    Document   : Autore
    Created on : 15-gen-2015, 10.14.15
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scelta autore</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
        <%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <script>
            function foo(modulo){
                if (modulo.autore.value=="")
                    alert("Devi selezionare un autore");
                else{
                    document.modulo.action = "Ricerca_Autore.jsp";
                    document.modulo.submit();
                }
            }
        </script>
    </head>
    <body>
        <h1>Scegli un autore</h1>
        <%!
            ResultSet rs;
            String user,query;
        %>
        <%
            query = "select Username From Utente";
            rs = Database.query(query);
            if (!rs.next())
                out.println("<p>Al momento non esistono documenti pubblicati</p>");
            else{
                rs.previous();
            
        %>
        <form name="modulo" method="post">
            <select name="autore" multiple="multiple">
                <%
                    while(rs.next()){
                        user = rs.getString(1);
                        out.println("<option value='"+user+"'>"+user+"</option>");
                    }
                }    
                %>
            </select>
            <input type ="button" value="Cerca" onclick="foo(modulo)">
        </form>
        
    </body>
</html>
