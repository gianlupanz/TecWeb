<%-- 
    Document   : Invito
    Created on : 10-gen-2015, 13.50.13
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
        <title>Invita Utente</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
        <%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <script>
            function foo(p1,p2){
                if (p1.value != "" && p2.value != ""){
                    document.modulo4.action='VerificaInvito';
                    document.modulo4.submit();
                }
                else
                    alert ("Devi selezionare un utente e un documento");
            }
        </script>
    </head>
    
    <body>
        <%!
            
            ResultSet rs;
            String utente,user,query,titolo;
            
        %>
        <% 
        out.println("<h1>Invito Condivisione Documento</h1>");
            user = session.getAttribute("Username").toString();
            query = "select Username from Utente Where Username<>'"+user+"';";
            rs = Database.query(query);
            out.println("<form method=post name=modulo4>");
            out.println("<select name=prova1 multiple=multiple>");
            while (rs.next()){
                    utente = rs.getString(1);   
                   out.println("<option value = '"+utente+"'>"+utente+"</option>");
            }
            out.println("</select>");
                query = "select Titolo from DocumentiDaPubblicare";
                rs = Database.query(query);
                out.println("<select name=prova2 multiple=multiple>");  
                while (rs.next()){                  
                    titolo = rs.getString(1);
                    titolo="\""+titolo+"\"";
                    out.println("<option value = "+titolo+"> "+titolo+" </option>");
            }
                out.println("</select><br>");
                out.println("<input type=button name=pulsante2 value=Condividi onclick=foo(prova1,prova2)>");
                out.println("</form><br><br>");
         %>

        <a href="Crea_Pagina_Editore.jsp">
            <button name="pulsante">Torna alla Home</button>
        </a>
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
