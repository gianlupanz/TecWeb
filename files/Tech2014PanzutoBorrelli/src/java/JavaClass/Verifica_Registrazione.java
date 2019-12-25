/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClass;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.swing.*;


/**
 *
 * @author iAngelo
 */
@WebServlet(name = "registrazione", urlPatterns = {"/registrazione"})
public class Verifica_Registrazione extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        ResultSet RisultatoQuery;  
            String indirizzo;
            try {
                
                 String Nome=request.getParameter("Nome");
                 String Cognome=request.getParameter("Cognome");
                 String Password=request.getParameter("Password");
                 String Username=request.getParameter("Username");
                 
                String Query="Select Nome From Utente Where Username='"+Username+"';";


                RisultatoQuery=Database.query(Query);
    
                 if(RisultatoQuery.next())
                 { 
                   
                    indirizzo="/Registrazione_Fallita.jsp"; 
                    doForward(indirizzo,request,response);
                    
                 }
                 
                 else
                 {
 
                         String Query2="Insert into Utente Values('"+Nome+"','"+Cognome+"','"+Password+"','"+Username+"');";
                         Database.operation(Query2);
                        indirizzo="/Registrazione_OK.jsp"; 
                        doForward(indirizzo,request,response);      
                 }
                      
            }
            
            catch(SQLException e)
            {
                //System.err.println("ERRORE DALLA PRIMA QUERY DI CONTROLLO DI USERNAME ESISTENTE");
                //System.err.println(e.getCause() + e.getMessage());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Verifica_Registrazione.class.getName()).log(Level.SEVERE, null, ex);
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
