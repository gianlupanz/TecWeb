/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iAngelo
 */
@WebServlet(name = "NotificaPubblicazione", urlPatterns = {"/NotificaPubblicazione"})
public class NotificaPubblicazione extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
        String query,utente,indirizzo,risposta2,mittente,temp,ed1,ed2,ed3,ed4,ed5;
        int count,count2;
        HttpSession session =request.getSession();
        HttpSession Notifica =request.getSession();
        String titolo = request.getParameter("documento");
        titolo="\""+titolo+"\"";
        String risposta= request.getParameter("risposta");
        utente= session.getAttribute("Username").toString();
        ResultSet rs;
        risposta2="Accetta";
        PrintWriter out = response.getWriter();
             Object temp2=session.getAttribute("Username");

   if(temp2!=null)
     {
         query="Select Mittente From NotifichePubblicazione Where TitoloDocumento="+titolo+" And Destinatario ='"+utente+"';";
         rs=Database.query(query);
         rs.next();
         mittente=rs.getString(1);
        
        if((risposta2.compareTo(risposta))==0)
        {
          
              query="Update NotifichePubblicazione SET Risposta=1 WHERE TitoloDocumento="+titolo+" AND Destinatario='"+utente+"';";
              Database.operation(query);
                query="Select count(*) From NotifichePubblicazione Where TitoloDocumento="+titolo+" AND Risposta=1";
                rs=Database.query(query);
                rs.next();
                temp=rs.getString(1);
                count=Integer.parseInt(temp);                
                //Qui inizia il controllo per sapere quanti editori ci sono
                query="Select * From DocumentiDaPubblicare Where Titolo="+titolo+" AND (Editore1<>'NULL' AND Editore2<>'NULL' AND Editore3<>'NULL' AND Editore4<>'NULL' AND Editore5<>'NULL')";
                rs=Database.query(query);
                if(rs.next())
                    count2=4;
                else
                {
                    query="Select * From DocumentiDaPubblicare Where Titolo="+titolo+" AND (Editore1<>'NULL' AND Editore2<>'NULL' AND Editore3<>'NULL' AND Editore4<>'NULL')";
                    rs=Database.query(query);
                     if(rs.next())
                        count2=3;
                     else
                     {
                        query="Select * From DocumentiDaPubblicare Where Titolo="+titolo+" AND (Editore1<>'NULL' AND Editore2<>'NULL' AND Editore3<>'NULL')";
                        rs=Database.query(query); 
                        if(rs.next())
                            count2=2;
                        else
                        
                             count2=1;      
                     }                   
                }

                System.out.println(count2);
                System.out.println(count);
                if(count2==count)//Il documento è pronto per essere pubblicato perchè tutti hanno dato adesione
                {
                    
                    query="Delete From NotifichePubblicazione Where TitoloDocumento="+titolo+";";
                    Database.operation(query);
                    query="Select Editore1,Editore2,Editore3,Editore4,Editore5 From DocumentiDaPubblicare Where Titolo="+titolo+"";
                    rs=Database.query(query);
                    rs.next();
                    ed1=rs.getString(1);
                    ed2=rs.getString(2);
                    ed3=rs.getString(3);
                    ed4=rs.getString(4);
                    ed5=rs.getString(5);
                    
                        query="Select Parte1,Parte2,Parte3,Parte4,Parte5 From DocumentiDaPubblicare Where Titolo="+titolo+";";
                        rs=Database.query(query);
                        rs.next();
                        String Parte1=rs.getString(1);
                        String Parte2=rs.getString(2);
                        String Parte3=rs.getString(3);
                        String Parte4=rs.getString(4);
                        String Parte5=rs.getString(5);
                        String TestoCompleto=""+Parte1;
                        if(Parte2!="")
                        {
                            TestoCompleto+=Parte2;
                        }
                         if(Parte3!="")
                        {
                            TestoCompleto+=Parte3;
                        }
                          if(Parte4!="")
                        {
                            TestoCompleto+=Parte4;
                        }
                           if(Parte5!="")
                        {
                            TestoCompleto+=Parte5;
                        }
                    TestoCompleto="\""+TestoCompleto+"\"";
                    if(ed3==null && ed4==null && ed5==null)
                    {
                        query="Insert Into DocumentiPubblicati (Titolo,Editore,Editore2,Testo,DataPubbl) Values("+titolo+",'"+ed1+"','"+ed2+"',"+TestoCompleto+",curdate());";
                        Database.operation(query);
                         query="Insert Into Messaggi Values('"+ed1+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                        Database.operation(query);
                         query="Insert Into Messaggi Values('"+ed2+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                        Database.operation(query);
                    }
                        
                        else if(ed4==null && ed5==null)
                        {
                            query="Insert Into DocumentiPubblicati (Titolo,Editore,Editore2,Editore3,Testo,DataPubbl) Values("+titolo+",'"+ed1+"','"+ed2+"','"+ed3+"',"+TestoCompleto+",curdate());";
                            Database.operation(query);
                            query="Insert Into Messaggi Values('"+ed1+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                        Database.operation(query);
                         query="Insert Into Messaggi Values('"+ed2+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                        Database.operation(query);
                        query="Insert Into Messaggi Values('"+ed3+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                        Database.operation(query);
                            
                            
                        }    
                            else if(ed5==null)
                            { 
                                query="Insert Into DocumentiPubblicati (Titolo,Editore,Editore2,Editore3,Editore4,Testo,DataPubbl) Values("+titolo+",'"+ed1+"','"+ed2+"','"+ed3+"','"+ed4+"',"+TestoCompleto+",curdate());";
                                Database.operation(query);  
                                query="Insert Into Messaggi Values('"+ed1+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed2+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed3+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed4+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                
                            }    
                                else
                            {
                                
                                query="Insert Into DocumentiPubblicati (Titolo,Editore,Editore2,Editore3,Editore4,Editore5,Testo,DataPubbl) Values("+titolo+",'"+ed1+"','"+ed2+"','"+ed3+"','"+ed4+"','"+ed5+"',"+TestoCompleto+",curdate());";
                                Database.operation(query);
                                 query="Insert Into Messaggi Values('"+ed1+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed2+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed3+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed4+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                                query="Insert Into Messaggi Values('"+ed5+"','Avvenuta pubblicazione del documento con titolo:"+titolo+"');";
                                Database.operation(query);
                    
                            }
                    
                    
                    query="Delete From DocumentiDaPubblicare Where Titolo="+titolo+";";
                    Database.operation(query);
                    Notifica.setAttribute( "Notifica","Documento Pubblicato");
                    doForward("/PubblicazioneEffettuata.jsp",request,response); 
                    
                }
                else
                {
                        query="Insert Into Messaggi Values('"+mittente+"','"+utente+" ha ACCETTATO la pubblicazione del  documento con titolo:"+titolo+"');";
                        Database.operation(query);
                        Notifica.setAttribute( "Notifica","Grazie per la tua adesione");
                        doForward("/PubblicazioneEffettuata.jsp",request,response);
                }
         
        }
        
        else
        {
     
            query="Delete From NotifichePubblicazione Where TitoloDocumento="+titolo+";";
            Database.operation(query);
            query="Insert Into Messaggi Values('"+mittente+"','"+utente+" ha RIFIUTATO la pubblicazione del  documento con titolo:"+titolo+"');";
            Database.operation(query);
            Notifica.setAttribute( "Notifica","Pubblicazione Annullata");
            doForward("/PubblicazioneAnnullata.jsp",request,response); 
        }
    }//fine if 
   
    else
     {
         indirizzo="/Login_Errato.jsp"; 
         doForward(indirizzo,request,response); 
     }
   
        
    }
    
    private void doForward( String nuovaPagina, HttpServletRequest request,

HttpServletResponse response ) throws ServletException, IOException {

 

    RequestDispatcher dispatcher = request.getRequestDispatcher(nuovaPagina);

    dispatcher.forward(request,response);

}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NotificaPubblicazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NotificaPubblicazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
