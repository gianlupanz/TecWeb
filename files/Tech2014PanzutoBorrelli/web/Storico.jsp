
<%-- 
    Document   : Storico
    Created on : 13-gen-2015, 12.55.00
    Author     : gianluca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="ERRORE.jsp" %>
        <%@ page isThreadSafe="true" %>
<!DOCTYPE html>
<% 
    Object temp2=session.getAttribute("Username");
    if(temp2!=null)
    {
        
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Storico</title>
        <%@page import="JavaClass.Database"%>
        <%@page import="java.sql.*"  %>
         <link href="listadoc.css" rel="stylesheet" type="text/css">
         <script>
             function foo(mod){
                 if(mod.storico1.value=="")
                     alert("seleziona un testo");
                 else{
                     mod.action="VerificaStorico";
                     mod.submit();
                 }
             }
         </script>
    </head>
    <body>
        <%!
            HttpSession hs;
            ResultSet rs1;
            String query2,testo,titolo1,data,tmp1;
        %>
        <%
		
 
            testo = "";
            hs = request.getSession();
            titolo1 = hs.getAttribute("TitoloDocumento").toString();
            titolo1="\""+titolo1+"\"";
            out.println("<h1>Ecco lo storico del seguente documento: "
                    +titolo1+"</h1><br><br><br>");
            query2="select Data,Parte1,Parte2,Parte3,Parte4,Parte5 From Storico Where Titolo="+titolo1+";";
            rs1=Database.query(query2);
	    
            // Se non sono presenti versioni precedenti allora si ritorna alla pagina documento.jsp

            if (!rs1.next()){
                out.println("<p>Non esistono versioni precedenti</p>");
                out.println("<a href = 'Crea_Pagina_Editore.jsp'>"
                        + "<button name=pulsante>Torna alla Home</button> </a>");
                
            }

            // Altrimenti si stampano in una select con le seguenti stringhe: "Data: Testo". Le varie option
	    // hanno come valore la data.

            else{
                
        %>
        <form method="post" name ="storico" >
            <select name="storico1" multiple="multiple">
            <%
                rs1.previous();
                while(rs1.next()){
                    data = rs1.getString(1);
                    System.out.println(data);
                    for(int i = 2; i < 7; i++)
                        testo+=rs1.getString(i);
                    tmp1 = ""+data+": "+testo;
                    out.println("<option value = '"+data+"'>"+tmp1+"</option>");
                    testo="";
                }
            
            %>
            </select>
            <input type ="button" name="pulsante" value="ripristina selezionato" onclick="foo(storico)"/>
        </form>
            <br><br><br>
            <a href='Crea_Pagina_Editore.jsp'>
                <button name="pulsante">Ritorna alla HomePage</button>
            </a><br>
        <a href='Lista_Doc.jsp'>
            <button name="pulsante">Ritorna alla Lista</button>
        </a><br>
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
