<%-- 
    Document   : Crea_Pagina_Editore
    Created on : 9-gen-2015, 10.28.52
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
<%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<% 
    Object temp=session.getAttribute("Username");
    if(temp!=null)
    {
        session.setAttribute( "MODIFICA","INIZIO");
        %>
<html>
    <head>
        <title>Pagina Editore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="siteStyle.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div align = 'center' ><h1>Benvenuto <%out.println(temp);%> </h1></div>
        <div align ='right'>
            <a href='Logout.jsp'>
                 <button name="pulsante">Logout</button>
            </a>
        </div>
        <br><br>

            <a href="Creanuovo.jsp">
              <button name="pulsante">Crea nuovo documento</button>
            </a>
            <a href="Lista_Doc.jsp">
                 <button name="pulsante">Visualizza Documenti che Editi</button></a>
            <a href="NotifichePubblicazione.jsp">
                 <button name="pulsante">Notifiche di Pubblicazione</button></a>
            <a href="Notifiche_Condivisione.jsp">
                 <button name="pulsante">Notifiche di Condivisione</button></a>
            <a href="Messaggi.jsp">
                 <button name="pulsante">Messaggi</button></a>
            <a href="pubblicaDoc.jsp">
                 <button name="pulsante">Pubblica Documento</button></a>
            <a href="VerificaCondivisioneDocumenti">
                <button name="pulsante">Invita un Utente a condividere un Documento</button></a>
           <a href="Autore.jsp">
                <button name="pulsante">Ricerca Pubblicazioni per Nome Editore</button></a>
            <a href="Titolo.jsp">
                 <button name="pulsante">Ricerca Pubblicazioni per Titolo</button></a>

    </body>
</html>

<% } 
else
    {
        out.println("DEVI REGISTRARTI PER ENTRARE IN QUESTA PAGINA");
    
    %>
    
    <%@include file="Login.html" %>
    
    <% } %>
    