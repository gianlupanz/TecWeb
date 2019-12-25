<%-- 
    Document   : Creanuovo
    Created on : 10-gen-2015, 11.28.03
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
        <link href="siteStyle.css" rel="stylesheet" type="text/css">
        <title>Nuovo Documento</title>
   <script>
         
         function checkPatternChars4(nm,vlu,pattern,required){
            if ( required === undefined ) 
                {
                    required = false;
                } 
            
            if(vlu=="")
                {
                    alert("Campo "+nm+" vuoto");
                    return;
                }
            
            var result=vlu.search(pattern);
             if (result==-1)
                return true;
            else{
                 alert("Non puoi inserire caratteri accentati" );  
                  return false;
                
               }
    }
       
    
         function checkPatternChars(nm,vlu,pattern,required){
            if ( required === undefined ) 
                {
                    required = false;
                } 
            
            if(vlu=="")
                {
                    alert("Campo "+nm+" vuoto");
                    return;
                }
            if (!pattern.test(vlu))
              {
                    alert("Il campo "+nm+" non e\' valido!");
                    return false;
              }
            else  
                return true; 
                
                
    }
         function checkPatternChars2(nm,vlu,pattern,required){
                if ( required === undefined ) 
                     required = false;
                     
                if (!pattern.test(vlu))
                {
                    alert("Il campo "+nm+" non e\' valido!");
                    return false;
                }
                else  
                return true; 
                
        }
         function checkPatternChars3(vl1,vl2,vl3,vl4){
     
            if (vl1==""&&(vl2!=""||vl3!=""||vl4!="")){
                alert("Hai lasciato il campo 2 vuoto e non puoi scrivere testo nelle sezioni successive");
                return false;
            }
            if (vl2==""&&(vl3!=""||vl4!="")){
                alert("Hai lasciato il campo 3 vuoto e non puoi scrivere testo nelle sezioni successive");
                return false;
            }
            if (vl3==""&&(vl4!="")){
                alert("Hai lasciato il campo 4 vuoto e non puoi scrivere testo nelle sezioni successive");
                return false;
            }
            else  
                return true; 
                
    }
  
        function myFunction(modulo_creazione) {
            var pattern_testo=/[\x20-x7E]{10,10000}/;
            //var pattern_titolo=/[\x20-x7E]{3,30}/;
            var pattern_titolo=/[ìè+ùàò]/;
             var pattern_testo2=/[\x20-x7E]{0,10000}/;
            if(!checkPatternChars4('Titolo',modulo_creazione.titolo.value,pattern_titolo,true))              
                {          
                    modulo_creazione.titolo.focus();   
                    return;
                }
             
            if(!checkPatternChars('Testo(Parte1)',modulo_creazione.messaggio1.value,pattern_testo,true))
               {
                    modulo_creazione.messaggio.focus();
                    return ;
                }
            if(!checkPatternChars2('Testo(Parte2)',modulo_creazione.messaggio2.value,pattern_testo2,true))
               {
                    modulo_creazione.messaggio2.focus();
                    return ;
                }
             if(!checkPatternChars2('Testo(Parte3)',modulo_creazione.messaggio3.value,pattern_testo2,true))
               {
                    modulo_creazione.messaggio3.focus();
                    return ;
                }
             if(!checkPatternChars2('Testo(Parte4)',modulo_creazione.messaggio4.value,pattern_testo2,true))
               {
                    modulo_creazione.messaggio4.focus();
                    return ;
                }
               if(!checkPatternChars2('Testo(Parte5)',modulo_creazione.messaggio5.value,pattern_testo2,true))
               {
                    modulo_creazione.messaggio5.focus();
                    return ;
                }
               if(!checkPatternChars3(modulo_creazione.messaggio2.value,modulo_creazione.messaggio3.value,modulo_creazione.messaggio4.value,modulo_creazione.messaggio5.value))
               {
                    return ;
                } 
            else
               {
                 document.modulo_creazione.action='VerificaNuovo';// aggiungo attributi alla form 
                 document.modulo_creazione.submit();// è come se avessi premuto CLICCA su un pulsante per far partire la Form  
               }
        }

     </script>
    </head>
    <body>
        <h1>Scrivi Un Nuovo Documento</h1>
        <form method='post' name='modulo_creazione'>
            <br><br><br><br>
            <label>Titolo Documento(Min 3 Caratteri)</label><br>
            <textarea name="titolo" cols="30" rows="2"></textarea><br>
            <label>Testo Documento(Min 10 Caratteri)</label><br>
            <textarea name="messaggio1" cols="30" rows="10"></textarea><label>Parte1</label><br><br>
            <textarea name="messaggio2" cols="30" rows="10"></textarea><label>Parte2</label><br><br>
            <textarea name="messaggio3" cols="30" rows="10"></textarea><label>Parte3</label><br><br>
            <textarea name="messaggio4" cols="30" rows="10"></textarea><label>Parte4</label><br><br>
            <textarea name="messaggio5" cols="30" rows="10"></textarea><label>Parte5</label><br><br>
            <input type='button' value='crea' class="input" onClick="myFunction(modulo_creazione)">
            <input type="reset" value="Reset" class="input">
        </form>
         
         <form method="post" name="nascosto" >
                        <input type="hidden" name="Search"/>
         </form>
       
        <br><br>
        <a href='Crea_Pagina_Editore.jsp'>
            <button name="pulsante"> Torna alla pagina Editore</button> </a>
    </body>
    
</html>


<% } 
else
    {
        out.println("DEVI REGISTRARTI PER ENTRARE IN QUESTA PAGINA");
    
    %>
    
    <%@include file="Login.html" %>
    
    <% } %>