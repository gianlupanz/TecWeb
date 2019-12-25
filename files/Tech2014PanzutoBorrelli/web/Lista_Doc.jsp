<%-- 
    Document   : Lista_Doc
    Created on : 13-gen-2015, 9.46.07
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<% 
    Object temp=session.getAttribute("Username");
    if(temp!=null)
    {
        
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="EditoreHome.css" rel="stylesheet" type="text/css">
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <title>Lista Documenti</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
        <script>
         
            function foo(modulo){
                if (modulo.Documenti2.value== "")
                    alert("Devi selezionare un titolo");
                else{
                    modulo.action ='Documento.jsp';
                    modulo.submit();
                }
            }
            
        </script>
    </head>
    <body>
        <h1>Lista Documenti</h1><br><br>
        <%!
            ResultSet rs;
            String query,user,s;
        %>
        <%
        //Stampa la lista di tutti i documenti che l'utente condivide 
        //AGGIUNGEREMO UN CONTROLLO SU VERIFICA NEL CASO NON Viene selezionato nulla
            user = temp.toString();
            query = "select Titolo from DocumentiDaPubblicare where Editore1 = '"+user+"' or Editore2 = '"+user+"' or Editore3 = '"+user+"'or Editore4 = '"+user+"' or Editore5 = '"+user+"';";
            rs = Database.query(query);
            if (!rs.next()){
                out.println("<p>Non ci sono Documenti che stai modificando</p><br>");
                out.println("<a href = 'Crea_Pagina_Editore.jsp'><button name=pulsante> Torna alla Home </button></a>");
            }
            else{

        out.println("<form method = post name=doc2>");
        out.println("<label name=listadoc>Documenti che condividi<br>");
	 out.println("<select name=Documenti2 multiple=multiple>");
                            
                                rs.previous();
                                while (rs.next()){
                                    s = rs.getString(1);
                                    s="\""+s+"\"";
                                    out.println("<option value="+s+">"+s+"</option>");
                                      
                                }                        
       out.println("</select>");
       out.println("<input type =button value =Visualizza onclick=foo(doc2)>");
       out.println("</label>");
       out.println("</form><br><br>");
       out.println("<a href='Crea_Pagina_Editore.jsp'><button name=pulsante> Torna alla pagina Editore</button> </a>");
          
            }
            %>
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