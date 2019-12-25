<%-- 
    Document   : Modifica
    Created on : 13-gen-2015, 12.06.54
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<% 
    Object temp=session.getAttribute("Username");
    if(temp!=null)
    {
        
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="siteStyle.css" rel="stylesheet" type="text/css">
        <title>Modifica del documento</title>
        
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>

    </head>
    
    <body>
        <h1>Effettua la Modifica</h1>
        <%!
            ResultSet rs;
            String titolo,testo,query,parte;
            
        %>
        <%
        //session.setAttribute( "MODIFICA","LIBERO");
        //Il form darà la possibilità di modificare la parte di testo precedentemente selezionata(Sezione.jsp)
           titolo = session.getAttribute("TitoloDocumento").toString();
           parte = session.getAttribute("Parte").toString();
           out.println("Parte"+parte);
           titolo="\""+titolo+"\"";
           query = "select Parte"+parte+" from DocumentiDaPubblicare where Titolo ="+titolo+";";
           rs = Database.query(query);
           rs.next();
           testo = rs.getString(1);
           
           
        %>       
        <form method='post' name='editing' action='VerificaEdit2'>
            <%
                out.println("<textarea name='testo' cols='30' rows='10'>"+testo+"</textarea><br><br>");
            %>
            <input type='submit' name="pulsante" value='modifica' class="input">
             <input type="reset" value="Reset" class="input">
        </form>
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