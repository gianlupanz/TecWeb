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
@WebServlet(name = "NotificaCondivisione", urlPatterns = {"/NotificaCondivisione"})
public class NotificaCondivisione extends HttpServlet {

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
        
        String query,utente,indirizzo,risposta2,mittente;
        HttpSession session =request.getSession();
         HttpSession Notifica_no =request.getSession();
        String titolo = request.getParameter("documento");
        String titolo_temp=titolo;
        titolo="\""+titolo+"\"";
        String risposta= request.getParameter("risposta");
        utente= session.getAttribute("Username").toString();
        ResultSet rs;
        risposta2="Accetta";
                Object temp2=session.getAttribute("Username");

   if(temp2!=null)
     {
         query="Select * From DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore1<>'NULL' AND Editore2<>'NULL' AND Editore3<>'NULL' AND Editore4<>'NULL' AND Editore5<>'NULL';";
        rs=Database.query(query);
         if(!rs.next()){
         
        query="Select Mittente From NotificheCondivisione Where TitoloDocumento="+titolo+" And Destinatario ='"+utente+"';";
        rs=Database.query(query);
        rs.next();
        mittente=rs.getString(1);
        
       
        
        query = "delete from NotificheCondivisione where TitoloDocumento="+titolo+""
                + " and Destinatario ='"+utente+"';";
        
        Database.operation(query);
        
        
        if((risposta2.compareTo(risposta))==0)
         {
             String messaggio=""+utente+" ha accettato di condividere il documento con titolo:"+titolo_temp+"";
              messaggio="\""+messaggio+"\"";
              query="Insert Into Messaggi Values('"+mittente+"',"+messaggio+");";
              Database.operation(query);

            query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore2<>'NULL'";
            rs = Database.query(query);
                if(!rs.next())              
                   query="update DocumentiDaPubblicare SET Editore2='"+utente+"' WHERE Titolo="+titolo+";";            
                else
                {
                    query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore3<>'NULL'";
                    rs = Database.query(query); 
                    if(!rs.next())                   
                        query="update DocumentiDaPubblicare SET Editore3='"+utente+"' WHERE Titolo="+titolo+";";      
                    else
                    {
                         query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore4<>'NULL'";
                         rs = Database.query(query); 
                         if(!rs.next())
                            query="update DocumentiDaPubblicare SET Editore4='"+utente+"' WHERE Titolo="+titolo+";";  
                         else
                             query="update DocumentiDaPubblicare SET Editore5='"+utente+"' WHERE Titolo="+titolo+";"; 
                    }
                }
              Database.operation(query);
              indirizzo = "/NotificaAccettata.jsp";
              doForward(indirizzo,request,response);
              
         }
        
        else
        {
         String messaggio=""+utente+" ha Rifiutato di condividere il documento con titolo:"+titolo_temp+"";
          messaggio="\""+messaggio+"\"";
            query="Insert Into Messaggi Values('"+mittente+"',"+messaggio+");";
              Database.operation(query);
              Notifica_no.setAttribute("Notifica_no","Ok,Non condividi questo Documento");
               indirizzo = "/NotificaRifiutata.jsp";
              doForward(indirizzo,request,response);
            
        }
        
      }
    else
         {
             query = "delete from NotificheCondivisione where TitoloDocumento="+titolo+""
                + " and Destinatario ='"+utente+"';";
              Database.operation(query);
             Notifica_no.setAttribute("Notifica_no","Hai accettato tardi,non puoi piu' condividere");
              indirizzo = "/NotificaRifiutata.jsp";
              doForward(indirizzo,request,response);
         
         }//fine if2
    }//fine if1 
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
            Logger.getLogger(NotificaCondivisione.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NotificaCondivisione.class.getName()).log(Level.SEVERE, null, ex);
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
