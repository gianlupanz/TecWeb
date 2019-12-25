<%-- 
    Document   : Documento
    Created on : 13-gen-2015, 9.57.18
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
        <title>Documento</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
    </head>
    <body>
        <%!
            ResultSet rs;
            String query,user,titolo;
        %>
        <% 
            //Una volta selezionato uno dei documenti(Da Lista_Doc.jsp) qui visualizzerò il testo del documento
            //e daremo l'opportunità all'utente di scegliere di modificare oppure visualizzare lo Storico
            titolo = request.getParameter("Documenti2");
            System.out.println("NEL DOCUMENTO.JSP: "+titolo+"");
            session.setAttribute("TitoloDocumento",titolo);
            out.println("<h1>Ecco il documento scelto: "+titolo+"</h1><br><br>");
            titolo="\""+titolo+"\"";
            query = "select Parte1,Parte2,Parte3,Parte4,Parte5 from DocumentiDaPubblicare where Titolo ="+titolo+";";
            rs = Database.query(query);
            rs.next();
            titolo = "";
            for (int i = 1; i < 6; i++){
                titolo += rs.getString(i);
            }
            out.println("<p>Testo: <br>"+titolo+"</p><br><br>");
        %>
        <a href="Storico.jsp"> 
            <button name=pulsante> Visualizza Storico</button>
        </a>    
        <a href="Sezione.jsp"> 
            <button name=pulsante> Effettua una modifica</button>
        </a>
        <br>
        <a href="Crea_Pagina_Editore.jsp">
            <button name=pulsante> Torna alla pagina Editore</button></a><br>
        <a href="Lista_Doc.jsp">
            <button name=pulsante> Ritorna alla Lista</button></a>
    </body>
</html>
<% 
    } else {
        out.println("<h1>DEVI EFFETTUARE IL LOGIN PER ENTRARE IN QUESTA PAGINA</h1>");
    %>
    
    <%@include file="Login.html" %>
    
<% 
    } 

%>