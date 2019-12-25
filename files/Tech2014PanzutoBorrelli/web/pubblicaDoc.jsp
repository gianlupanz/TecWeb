<%-- 
    Document   : prova_lettura_da_html
    Created on : 12-gen-2015, 12.10.53
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
        
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pubblicazione documento</title>
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
        <script>
            function foo(mod){
                if(mod.documenti.value=="")
                    alert("Seleziona un documento");
                else{
                    mod.action = "verifica_pub_1";
                    mod.submit();
                }
            }
        </script>
    </head>
    <body>
        <%!
            ResultSet rs;
            String query,user,x;
            
        %>
        <%
            //Stampa la lista di documenti che si possono pubblicare
            user = temp.toString();
            query = "select Titolo from DocumentiDaPubblicare where Editore1 = '"+user+"' or Editore2 = '"+user+"' or Editore3 = '"+user+"'or Editore4 = '"+user+"' or Editore5 = '"+user+"';";
            rs = Database.query(query);
            if (!rs.next()){
                out.println("<p>Non sei editore di nessun Documento</p>");
                out.println("<a href = 'Crea_Pagina_Editore.jsp'><button name=pulsante>Torna alla Home</button></a>");
            }
            else{
             
        %>
        <form method = "post" name = "doc">
	 	<label>
                    Documenti che condividi<br>
                    <br><br>
	  		<select name="documenti" multiple="multiple">
                            <%
                                rs.previous();
                                while (rs.next()){
                                    x=rs.getString(1);
                                    x="\""+x+"\"";
                                    out.println("<option value="+x+">"+x+"</option>");
                                    
                                }
            
                            %>
                            
	  		</select>
                    <input type = "button" value = "Pubblica" onclick="foo(doc)"/>
                </label><br><br>
            <a href="Crea_Pagina_Editore.jsp">
                <button name=pulsante1> Torna alla pagina Editore</button>
            </a>
                        <%
            }
                        %>
        </form>
    </body>
<</html>

<% 
    } else {
        out.println("DEVI EFFETTUARE IL LOGIN PER ENTRARE IN QUESTA PAGINA");
    %>
    
    <%@include file="Login.html" %>
    
<% 
    } 

%>