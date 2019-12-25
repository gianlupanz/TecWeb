<%-- 
    Document   : NotificaRifiutata
    Created on : 20-gen-2015, 9.46.50
    Author     : iAngelo
--%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<% 
    String  Notifica= session.getAttribute("Notifica_no").toString();  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"siteStyle.css\" rel=\"stylesheet\"type=\"text/css\">");
            out.println("<title>Errore</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>"+Notifica+"</p>");
             out.println("<a href=Crea_Pagina_Editore.jsp>"
                     + "<button name=pulsante>Torna alla Pagina Editore</button></a>");
            out.println("</body>");
            out.println("</html>");
            
       session.setAttribute("Notifica_no",null);
       
       
%>