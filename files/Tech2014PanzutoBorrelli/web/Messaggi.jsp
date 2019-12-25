<%-- 
    Document   : Messaggi
    Created on : 13-gen-2015, 21.33.40
    Author     : gianluca
--%>
<% 
    Object temp=session.getAttribute("Username");
    if(temp!=null)
    {
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="siteStyle.css" rel="stylesheet" type="text/css">
        <title>Messaggi</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
    </head>
    <body>
        <%!
            ResultSet rs;
            String user,query;
        %>
        <%
            user = session.getAttribute("Username").toString();
            query = "select Testo from Messaggi where Destinatario = '"+user+"';";
            rs = Database.query(query);
            if (!rs.next())
                out.println("<br><br><h1>Non hai ricevuto messaggi</h1><br><br>");
            else{
                rs.previous();
                while(rs.next()){
                    out.println("<p>"+rs.getString(1)+"</p><br>");
                }
                out.println("<br>");
            }
        %>
        <a href="Crea_Pagina_Editore.jsp">
            <button name=pulsante> Torna alla pagina Editore</button></a>
    </body>
</html>
<% 
    } else {
        out.println("DEVI EFFETTUARE IL LOGIN PER ENTRARE IN QUESTA PAGINA");
    %>
    
    <%@include file="Login.html" %>
    
<% 
    } 

%>
