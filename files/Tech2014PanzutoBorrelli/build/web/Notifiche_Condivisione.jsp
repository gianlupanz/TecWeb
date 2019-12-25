<%--
    Document   : Notifiche_Condivisione
    Created on : 17-gen-2015, 17.29.55
    Author     : gianluca
--%>
<% 
    Object temp2=session.getAttribute("Username");
    if(temp2!=null)
    {
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<html>
    <head>
        <script>
            
            function valButton(btn)
        {   
            var cnt = -1;
            for (var i = btn.length - 1; i > -1; i--) 
            {
                if (btn[i].checked)
                    {
                        cnt = i;
                        i = -1;
                    }
             }
        if (cnt > -1)
            return btn[cnt].value;
        else
            return null;
        }
            function foo(modulo){
                var btn = valButton(modulo.risposta);
                
                if (btn== null)
                    alert("Devi accettare/rifiutare la notifica");
                else{
                    modulo.action='NotificaCondivisione';
                    modulo.submit();
                }
                   
            }
        </script>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifiche Condivisione</title>
        <%@page import="java.sql.*" %>
        <%@page import="JavaClass.Database" %>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%!
            ResultSet rs2;
            String query2,utente2,tmp,tmp1;
            
        %>
        <%
        
            utente2 = session.getAttribute("Username").toString();
            query2 = "Select Mittente,TitoloDocumento from NotificheCondivisione where Destinatario = '"+utente2+"';";
            rs2 = Database.query(query2);
            if (!rs2.next()){
                out.println("<p>Non ci sono notifiche di condivisione</p>");
                
        %>
        
        
        
        <%@include file="Crea_Pagina_Editore.jsp" %>
        
        <%        
            }else{
                rs2.previous();
                
        %>
        
            
        <%
                int i=0;
                String count;
                while(rs2.next()){
                    i++;
                    count=String.valueOf(i).toString();
                    tmp = rs2.getString(1);
                    tmp1 = rs2.getString(2);
                    tmp1="\""+tmp1+"\"";
                    out.println("<form method='post' name='condiviso"+count+"'>");
                    out.println("<p>l'utente "+tmp+" vuole condividere con te "
                            + "il seguente documento: "+tmp1+"</p>");
                    out.println("<input type='hidden' name='documento' value="+tmp1+">");
                    out.println("<label for='accetta'>Accetta</label>");
                    out.println("<input type = 'radio' name = 'risposta' id='accetta'"
                            + " value='Accetta'><br>");
                    out.println("<label for='rifiuta'>Rifiuta</label>");
                    out.println("<input type = 'radio' name = 'risposta' id='rifiuta'"
                            + " value='Rifiuta'><br>");
                    out.println("<input type='button' name=pulsante value='invia"+count+"' onclick=foo(condiviso"+count+")>");
                    out.println("</form>");
                }
        %>    
            
        <%        
            }
        %>
    </body>
</html>
<% 
    } else {
        out.println("<p>DEVI EFFETTUARE IL LOGIN PER ENTRARE IN QUESTA PAGINA</p>");
    %>
    
        <%@include file="Login.html" %>
    
<% 
    } 

%>