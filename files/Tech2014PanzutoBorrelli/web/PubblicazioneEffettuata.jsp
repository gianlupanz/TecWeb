<%-- 
    Document   : PubblicazioneEffettuata
    Created on : 22-gen-2015, 10.22.43
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<% 
    String  Notifica= session.getAttribute("Notifica").toString();  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"siteStyle.css\" rel=\"stylesheet\"type=\"text/css\">");
            out.println("<title>Errore</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Bene: "+Notifica+"</p>");
             out.println("<a href=Crea_Pagina_Editore.jsp>"
                     + "<button name=pulsante>Torna alla Pagina Editore</button></a>");
            out.println("</body>");
            out.println("</html>");
            
       session.setAttribute("Notifica",null);
       
       
%>