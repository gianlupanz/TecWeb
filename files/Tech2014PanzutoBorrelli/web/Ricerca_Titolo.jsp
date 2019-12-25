<%-- 
    Document   : Ricerca_Titolo.jsp
    Created on : 15-gen-2015, 10.31.44
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
            
            String query,titolo;
            ResultSet rs;
        
        %>
        <%
            titolo = request.getParameter("titolo");
            titolo="\""+titolo+"\"";
            query = "SELECT Editore,Editore2,Editore3,Editore4,Editore5,Testo FROM DocumentiPubblicati where Titolo ="+titolo+";";;
            rs = Database.query(query);
            while(rs.next()){
                out.println("<p>Autore: "+rs.getString(1)+"</p><br>");
                if(rs.getString(2)!=null)
                out.println("<p>Autore2: "+rs.getString(2)+"</p><br>");
                if(rs.getString(3)!=null)
                    out.println("<p>Autore3: "+rs.getString(3)+"</p><br>");
                  if(rs.getString(4)!=null)
                    out.println("<p>Autore4: "+rs.getString(4)+"</p><br>");
                    if(rs.getString(5)!=null)
                    out.println("<p>Autore5: "+rs.getString(5)+"</p><br>");
                out.println("<p>Testo: "+rs.getString(6)+"</p><br><br><br>");
            }
            
        %>
        
    </body>
</html>
