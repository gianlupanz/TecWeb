<%-- 
    Document   : Ricerca_Autore
    Created on : 15-gen-2015, 10.23.07
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autori</title>
        <%@page import="java.sql.*" %>
        <%@page import="JavaClass.Database" %>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Elenco documenti</h1>
        <%!
            String query,utente;
            ResultSet rs;
        %>
        <%
            utente = request.getParameter("autore");
            query = "SELECT Titolo,Testo FROM DocumentiPubblicati where Editore='"+utente+"' OR Editore2='"+utente+"' OR Editore3='"+utente+"' OR Editore4='"+utente+"' OR Editore5='"+utente+"';";
            rs = Database.query(query);
            if(rs.next())
            {
                rs.previous();
                while(rs.next()){
                        out.println("<p>Titolo: "+rs.getString(1)+"</p><br>");
                        out.println("<p>Testo: "+rs.getString(2)+"</p><br><br><br>");
                    }
            }
            else
                out.println("<p>Questo Editore non ha partecipato alla pubblicazione di nessun documento</p>"); 
        %>
        
    </body>
</html>
