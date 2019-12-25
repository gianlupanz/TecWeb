<%-- 
    Document   : ImpossibileInvitare
    Created on : 15-gen-2015, 17.33.32
    Author     : iAngelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<% 
    String  Errore = session.getAttribute("Errore").toString();  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"siteStyle.css\" rel=\"stylesheet\"type=\"text/css\">");
            out.println("<title>Errore</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>ERRORE:"+Errore+"</p>");
             out.println("<a href=Crea_Pagina_Editore.jsp>"
                     + "<button name=pulsante>Torna alla Pagina Editore</button></a>");
            out.println("</body>");
            out.println("</html>");
            
       session.setAttribute("Errore",null);
     
%>



