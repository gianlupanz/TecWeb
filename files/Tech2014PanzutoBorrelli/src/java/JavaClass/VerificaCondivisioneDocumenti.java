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
@WebServlet(name = "VerificaCondivisioneDocumenti", urlPatterns = {"/VerificaCondivisioneDocumenti"})
public class VerificaCondivisioneDocumenti extends HttpServlet {

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
        
        ResultSet RisultatoQuery;
        HttpSession session =request.getSession();
        String Username,Query;
        Username= session.getAttribute("Username").toString();
        HttpSession err =request.getSession();
        Object temp=session.getAttribute("Username");
      if(temp!=null){
          
          Query="Select Username From Utente Where Username<>'"+Username+"';";
            RisultatoQuery = Database.query(Query);
          
        if(RisultatoQuery.next())
        {
            //Verifico che l'utente che vuole condividere lo può fare,cioè lui almeno condivide il documento
            Query="Select * From DocumentiDaPubblicare Where Editore1='"+Username+"' OR Editore2='"+Username+"' OR Editore3='"+Username+"' OR Editore4='"+Username+"' OR Editore5='"+Username+"';";
             RisultatoQuery = Database.query(Query);
             if(RisultatoQuery.next())
                 doForward("/Invito.jsp",request,response);
             
             else
             {
                 err.setAttribute( "Errore","L'utente non condivide nessun documento"); 
                 doForward("/ImpossibileInvitare.jsp",request,response); 
        
             }
        }
             
        
        else
        {
            err.setAttribute( "Errore","Non ci sono altri utenti");
            doForward("/ImpossibileInvitare.jsp",request,response);
        }
        
        
    }
        else
            {
                String indirizzo="/Login_Errato.jsp"; 
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
            Logger.getLogger(VerificaCondivisioneDocumenti.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerificaCondivisioneDocumenti.class.getName()).log(Level.SEVERE, null, ex);
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
