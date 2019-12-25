<%-- 
    Document   : Sezione
    Created on : 13-gen-2015, 11.31.31
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
        <link href="listadoc.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scelta Sezione</title>
        <script>
            function foo(modulo){
                if(modulo.ParteDoc.value=="")
                    alert("Devi selezionare una parte");
                else
                {
                    modulo.action = "VerificaEdit1";
                    modulo.submit();
                }
            }
        </script>
    </head>
    <body>
        <form method = "post" name = "parte" >
	 	<label>Scegli la parte che vuoi modificare<br>
	  		<select name="ParteDoc" multiple="multiple">
                            <option value='1'>Parte 1</option>
                            <option value='2'>Parte 2</option>
                            <option value='3'>Parte 3</option>
                            <option value='4'>Parte 4</option>
                            <option value='5'>Parte 5</option>
	  		</select>
                    <input type = "button" value = "Modifica" onclick="foo(parte)"/>
	 	</label>
        </form>  
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