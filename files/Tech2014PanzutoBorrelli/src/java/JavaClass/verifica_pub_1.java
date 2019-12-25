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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iAngelo
 */
@WebServlet(name = "verifica_pub_1", urlPatterns = {"/verifica_pub_1"})
public class verifica_pub_1 extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String titolo=request.getParameter("documenti");
            titolo="\""+titolo+"\"";
            ResultSet Rs;
            String Query2;
            HttpSession session =request.getSession();
            HttpSession Notifica =request.getSession();
            String Editore= session.getAttribute( "Username").toString();
               Object temp=session.getAttribute("Username");

   if(temp!=null)
     {   
            String query="Select Editore1,Editore2,Editore3,Editore4,Editore5,Parte1,Parte2,Parte3,Parte4,Parte5 From DocumentiDaPubblicare Where Titolo="+titolo+";";
            Rs=Database.query(query);
            Rs.next();
            String a=Rs.getString(1);
            String b=Rs.getString(2);
            String c=Rs.getString(3);
            String d=Rs.getString(4);
            String e=Rs.getString(5);
            String Parte1=Rs.getString(6);
            String Parte2=Rs.getString(7);
            String Parte3=Rs.getString(8);
            String Parte4=Rs.getString(9);
            String Parte5=Rs.getString(10);
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
            
            if(b==null && c==null && d==null && e==null)//Il documento non è condiviso da nessuno
            {
                TestoCompleto="\""+TestoCompleto+"\"";
                String Query="Insert Into DocumentiPubblicati (Titolo,Editore,Testo,DataPubbl) Values("+titolo+",'"+Editore+"',"+TestoCompleto+",curdate());";
                Database.operation(Query);
                Query2="Delete From DocumentiDaPubblicare Where Titolo="+titolo+";";
                Database.operation(Query2);
                String indirizzo="/PubblicazioneEffettuata.jsp";
                Notifica.setAttribute( "Notifica","Documento Pubblicato");
                doForward(indirizzo,request,response); 
                
            }
            
            else//Il documento è condiviso
            {
                 String indirizzo;
                
                    Query2="Select * From NotifichePubblicazione Where Destinatario='"+b+"' OR Mittente='"+b+"' AND TitoloDocumento="+titolo+";";
                    Rs=Database.query(Query2);
                     if(!Rs.next())
                     {
                         if((a.compareTo(Editore))!=0)
                        {
                            Query2="Insert Into NotifichePubblicazione(TitoloDocumento,Destinatario,Mittente) Values("+titolo+",'"+a+"','"+Editore+"');"; 
                            Database.operation(Query2);
                        }
                        if((b.compareTo(Editore))!=0)
                        {
                            Query2="Insert Into NotifichePubblicazione(TitoloDocumento,Destinatario,Mittente) Values("+titolo+",'"+b+"','"+Editore+"');"; 
                            Database.operation(Query2);
                        }
                        if(c!=null && (c.compareTo(Editore))!=0)
                        {
                          
                          Query2="Insert Into NotifichePubblicazione(TitoloDocumento,Destinatario,Mittente) Values("+titolo+",'"+c+"','"+Editore+"');"; 
                          Database.operation(Query2);
                        
                        }
                         if(d!=null && (d.compareTo(Editore))!=0)
                        {
                          Query2="Insert Into NotifichePubblicazione(TitoloDocumento,Destinatario,Mittente) Values("+titolo+",'"+d+"','"+Editore+"');"; 
                          Database.operation(Query2);
                        }
                          if(e!=null && (e.compareTo(Editore))!=0)
                        {
                          Query2="Insert Into NotifichePubblicazione(TitoloDocumento,Destinatario,Mittente) Values("+titolo+",'"+e+"','"+Editore+"');"; 
                          Database.operation(Query2);
                        }
                          Notifica.setAttribute( "Notifica","Documento in fase di Pubblicazione");
                         indirizzo="/PubblicazioneEffettuata.jsp"; 
                         doForward(indirizzo,request,response);
                        
                    }
                     else//Documento già in fase di pubblicazione
                     {
                         Notifica.setAttribute( "Notifica","Errore: Documento Gia' in fase di pubblicazione");
                         indirizzo="/PubblicazioneAnnullata.jsp"; 
                         doForward(indirizzo,request,response);
                     }
           }
    
        }//fine if 
        
        else
            {
                String indirizzo="/Login_Errato.jsp"; 
                doForward(indirizzo,request,response); 
                
            }
   
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
            Logger.getLogger(verifica_pub_1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(verifica_pub_1.class.getName()).log(Level.SEVERE, null, ex);
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

