<%-- 
    Document   : Titolo
    Created on : 15-gen-2015, 10.30.12
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scelta titolo</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <script>
            function foo(modulo){
                if (modulo.titolo.value == "")
                    alert("Devi selezionare un titolo");
                else
                {
                    document.modulo.action = 'Ricerca_Titolo.jsp';
                    document.modulo.submit();
                }
            }
        </script>
    </head>
    <body>
        <h1>Scegli un titolo</h1>
        <%!
            ResultSet rs;
            String title,query;
        %>
        <%
            query = "select Titolo from DocumentiPubblicati";
            rs = Database.query(query);
            if (!rs.next())
                out.println("<p>Al momento non esistono documenti pubblicati</p>");
            else{
                rs.previous();
        %>
        <form name="modulo" method="post">
            <select name="titolo" multiple="multiple">
                <%
                    while(rs.next()){
                        title = rs.getString(1);
                        title="\""+title+"\"";
                        out.println("<option value="+title+">"+title+"</option>");
                    }
                    
                %>
            </select>
            <input type ="button" value="Cerca" onclick="foo(modulo)">
        </form>
        <%
            }
        %>
    </body>
</html>
